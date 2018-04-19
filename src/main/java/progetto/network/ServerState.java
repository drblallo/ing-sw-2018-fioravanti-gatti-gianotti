package progetto.network;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class holds all the information at room level that are need to the server.
 * <p>
 * A server is composed by a list of room. Each room holds players.
 */
public final class ServerState implements Runnable
{

	private static final Logger LOGGER = Logger.getLogger(ServerState.class.getName());

	private final Map<Integer, AbstractRoom> rooms = new ConcurrentHashMap<>();
	private int lastID = 0;
	private int lastPlayerID = 0;
	private boolean isRunning = true;
	private final ISyncFactory factory;


	ServerState(ISyncFactory factory)
	{
		WaitingRoom waitingRoom = new WaitingRoom();
		rooms.put(-1, waitingRoom);
		new Thread(waitingRoom).start();
		this.factory = factory;
	}

	/**
	 * creates a room with provided name, rooms with the same name are allowed
	 *
	 * @param roomName the name of the new room
	 */
	void createRoom(String roomName)
	{
		Room room = new Room(roomName, lastID, factory.create());
		rooms.put(lastID, room);
		lastID++;
		LOGGER.log(Level.FINE,"spawning a new room");
		new Thread(room).start();
	}


	/**
	 * @return returns the room with that id.
	 */
	AbstractRoom getRoom(int roomID)
	{
		return rooms.get(roomID);
	}


	/**
	 * @return returns the room of the player with such id, false if such player does not exists.
	 */
	public AbstractRoom getRoomOfPlayer(int id)
	{
		for (AbstractRoom room : rooms.values())
		{
			if (room.getInfoFromID(id) != null)
				return room;
		}

		return null;
	}

	/**
	 * sets a player in a specified room
	 *
	 * @param roomID   the room where it should be spawned, if roomID == -1 then the player removed from his room
	 */
	public synchronized void placePlayer(String newName, int roomID, ServerConnection con)
	{
		if (con.getPlayerID() == -1)
			con.setID(getUnusedID());

		AbstractRoom oldRoom = getRoomOfPlayer(con.getPlayerID());

		if (oldRoom != null)
			oldRoom.enqueueRemoval(con.getPlayerID());

		AbstractRoom r = getRoom(roomID);
		if (r != null)
			r.enqueueAdd(newName, con.getPlayerID(), con);

		LOGGER.log(Level.FINE, "placed player in room {0}", roomID);
	}

	/**
	 * returns an unused id inside this server
	 *
	 * @return
	 */
	private int getUnusedID()
	{
		LOGGER.log(Level.FINE, "giving out a new id: {0}", lastPlayerID);
		int toReturno = lastPlayerID;
		lastPlayerID++;
		return toReturno;
	}

	public ServerStateView getView()
	{
		ServerStateView v = new ServerStateView();
		for (AbstractRoom r : rooms.values())
			v.addRoom(r.getName(), r.getRoomID(), r.getPlayerCount());

		return v;
	}

	/**
	 * deletes a room from this server
	 */
	private void deleteRoom(int roomID)
	{
		AbstractRoom r = getRoom(roomID);

		if (r != null)
			r.stop();

		LOGGER.log(Level.FINE, "room deleted");
		rooms.remove(r);
	}

	void stop()
	{
		isRunning = false;
	}

	private void tearDown()
	{
		for (int r : rooms.keySet())
			deleteRoom(r);
	}

	private void processAllRequest()
	{

		try
		{

			for (AbstractRoom r : rooms.values())
			{
				while (r.peekRequest() != null)
						r.popRequest().execute(this, r);
			}

			Thread.sleep(NetworkSettings.THREAD_CHECK_RATE);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.INFO, "somehow woke up: {0}", e.getMessage());
		}
	}

	public void run()
	{
		Thread.currentThread().setName("Server State Thread");
		while (isRunning)
			processAllRequest();

		tearDown();
	}

	public void broadcast(String message)
	{
		for (AbstractRoom room : rooms.values())
			room.broadcast(message);
	}

}
