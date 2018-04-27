package progetto.network;


import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract room is the class that holds the connections.
 * Abstract room implements runnable, a new thread must be started
 *
 * When a player sends a request, this request is processed by the thread spawned.
 * It ensures that request received by the same player are solved in order of arrival.
 * It DOES not ensure that requests received by different player are solved in order of arrival.
 */
abstract class AbstractRoom implements Runnable
{
	protected String name;
	protected final Map<Integer, Player> players = new ConcurrentHashMap<>();

	private final int id;
	private static final Logger LOGGER = Logger.getLogger(AbstractRoom.class.getName());
	private boolean isAlive = true;
	private final Queue<AbstractServerRequest> reqQueue = new ConcurrentLinkedQueue<>();
	private final Queue<IRoomRequest> roomRequests = new ConcurrentLinkedQueue<>();
	private final ISync syncOgg;

	abstract void setPlayerReady(int playerID, boolean ready);
	abstract void setPlayerChair(int playerID, int newChair);

	/**
	 *
	 * @return the unique id of this room
	 */
	final int getRoomID()
	{
		return id;
	}

	/**
	 *
	 * @return the first request in the request queue that have been received by players
	 */
	final AbstractServerRequest popRequest()
	{
		return reqQueue.poll();
	}

	/**
	 *
	 * @return the first request in the request queue, without poping it.
	 */
	final AbstractServerRequest peekRequest()
	{
		return reqQueue.peek();
	}

	/**
	 * insert a request in the queue of request that must be processed.
	 * @param request to be inserted
	 */
	final void offerRequest(AbstractServerRequest request)
	{
		reqQueue.offer(request);
	}

	/**
	 * Builds a room with specified name and id
	 */
	AbstractRoom(String roomName, int roomID, ISync ogg)
	{
		id = roomID;
		name = roomName;
		syncOgg = ogg;
	}

	/**
	 * @return the synchronization object used in this room
	 */
	final ISync getSyncOgg()
	{
		return syncOgg;
	}

	/**
	 * @return the current name of this room.
	 */
	final String getName()
	{
		return name;
	}

	/**
	 * this method is where all requests are performed.
	 * This ensures the absence of threading errors when the abstract room is updated, but requires that
	 * all modification are made inside a roomRequest.
	 */
	private void processAllCommand()
	{

		for (Player p : players.values()) //removes all players that are no longer connected
			if (!p.getHandler().isRunning())
				removePlayer(p.getPlayerID());

		for (Player p : players.values()) //evaluate all requests made from a player
		{
			while (p.peekRequest() != null)
			{
				LOGGER.log(Level.FINE, "Evaluating player request ");
				p.popRequest().execute(this, p.getHandler());
			}
		}

		while (roomRequests.peek() != null) //evaluate directly received by the room
			roomRequests.poll().execute(this, null);

		try
		{
			Thread.sleep(NetworkSettings.THREAD_CHECK_RATE); //goes to sleep for a while
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			LOGGER.log(Level.INFO, "woke up somehow: {0}", e.getMessage());
		}

	}

	/**
	 * implementation of the runnable interface
	 * set the thread name to Room Thread
	 */
	public final void run()
	{
		Thread.currentThread().setName("Room Thread "+getRoomID());
		while (isAlive)
		{
			processAllCommand();
		}
		tearDown();
	}

	/**
	 * returns the playerinfo related to the player inside this room.
	 *
	 * @param id the id of the player.
	 * @return null if there is no such player, its information otherwise.
	 */
	final Player getPlayerFromID(int id)
	{
		return players.get(id);
	}


	/**
	 * append a request to create a new player inside this room the next time all pending command are processed.
	 *
	 * @param playerName the name with which the player will be know inside this room
	 * @param connection the id of the player that is trying
	 */
	final void enqueueAdd(final String playerName, final ServerConnection connection)
	{
		roomRequests.offer((IRoomRequest) (room, serverConnection) -> addPlayer(playerName, connection));
	}

	final void enqueueRemoval(final int playerID)
	{
		roomRequests.offer((IRoomRequest) (room, serverConnection) -> removePlayer(playerID));
	}

	/**
	 * creates a new player inside this room, if such player does no exist. Triggers a playerJoinEvent.
	 *
	 * @param playerName the name of the new player.
	 */
	final void addPlayer(String playerName, ServerConnection handler)
	{
		Player info = new Player(playerName, handler);
		players.put(handler.getPlayerID(), info);
		notifyChange();
		if (getSyncOgg() != null)
			handler.onRoomChanged(getSyncOgg());
		LOGGER.fine("Player added to room");
	}

	/**
	 * remove a player from this room. Trigger a playerLeaveEvent if such player existed.
	 *
	 * @param playerID the player to be removed
	 */
	final void removePlayer(int playerID)
	{
		LOGGER.fine("Player removed from room");
		players.remove(playerID);
		notifyChange();
	}

	/**
	 * returns the player that is inside a particular chair
	 *
	 * @param chairID a number between 0 and MAX_CHAIR_NUMBER
	 * @return the player that is sitting in a particular chair, null if chairID is invalid or if nobody is sitting
	 * in that chair.
	 */
	final Player getPlayerOfChair(int chairID)
	{
		if (chairID < 0)
			return null;

		for (Player f : players.values())
			if (f.getChairID() == chairID)
				return f;

		return null;
	}

	/**
	 *
	 * @return the count of player inside this room. Returns observers as well.
	 */
	final int getPlayerCount()
	{
		return players.keySet().size();
	}

	/**
	 *
	 * @return the view of this room
	 */
	RoomView getView()
	{
		RoomView v = new RoomView(name, id);
		for (Player info : players.values()) //players is thread safe, since is a concurrent hash map
			v.addPlayer(info); //add player is thread safe

		return v;
	}

	/**
	 * stops the room thread. Clean up will be executed when the room thread desires.
	 */
	void stop()
	{
		isAlive = false;
	}

	/**
	 * closes all the player connections, calls onTearDown
	 */
	private void tearDown()
	{
		isAlive = false;
		for (Map.Entry<Integer, Player> p : players.entrySet())
		{
			p.getValue().getHandler().disconnect();
			removePlayer(p.getKey());
		}
		onTearDown();
	}

	/**
	 * send a message to every player inside the room
	 * @param message to be sent
	 */
	void broadcast(String message)
	{
		for (int p : players.keySet())
			sendMessage(p, message);
	}

	/**
	 * send to every player the new state of the room
	 */
	void notifyChange()
	{
		for (Player p : players.values())
			p.getHandler().onRoomModified(getView());
	}

	/**
	 * every derived class must implements how sync string are handled
	 * @param syncString the string that must be syncd
	 * @param callerID the id of the player that send the string
	 */
	abstract void processCommand(Serializable syncString, int callerID);

	/**
	 * called when the room is closing, it is called inside the room update thread, so every modification
	 * to the room is thread safe
	 *
	 * default implementation is do nothing, so there is no need to call super
	 */
	void onTearDown()
	{
		//default implementation is do nothing
	}

	/**
	 * send a message to a particular client
	 * @param targetID id of the target
	 * @param message string to be sent
	 */
	void sendMessage(int targetID, String message)
	{
		if (players.get(targetID) != null)
			players.get(targetID).getHandler().sendMessage(message);
	}
}