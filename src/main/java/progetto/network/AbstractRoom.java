package progetto.network;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractRoom implements Runnable
{
	protected String name;
	protected final Map<Integer, PlayerInfo> players = new ConcurrentHashMap<>();

	private final int id;
	private static final Logger LOGGER = Logger.getLogger(AbstractRoom.class.getName());
	private boolean isAlive = true;
	private final Queue<AbstractServerRequest> reqQueue = new ConcurrentLinkedQueue<>();
	private final Queue<AbstractRoomRequest> roomRequests = new ConcurrentLinkedQueue<>();
	private final ISync syncOgg;

	abstract void setPlayerReady(int playerID, boolean ready);
	abstract void setPlayerChair(int playerID, int newChair);


	final int getRoomID()
	{
		return id;
	}

	final AbstractServerRequest popRequest()
	{
		return reqQueue.poll();
	}

	final AbstractServerRequest peekRequest()
	{
		return reqQueue.peek();
	}

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

	private final void processAllCommand()
	{
		for (PlayerInfo p : players.values())
			if (!p.getHandler().isRunning())
				removePlayer(p.getPlayerID());

		for (PlayerInfo p : players.values())
		{
			while (p.peekRequest() != null)
			{
				LOGGER.log(Level.FINE, "Evaluating player request ");
				p.popRequest().execute(this, p.getHandler());
			}
		}

		while (roomRequests.peek() != null)
			roomRequests.poll().execute(this, null);

		try
		{
			Thread.sleep(NetworkSettings.THREAD_CHECK_RATE);
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			LOGGER.log(Level.INFO, "woke up somehow: {0}", e.getMessage());
		}

	}

	public final void run()
	{
		Thread.currentThread().setName("Room Thread");
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
	final PlayerInfo getInfoFromID(int id)
	{
		return players.get(id);
	}


	final void enqueueAdd(final String playerName, final int playerID, final ServerConnection connection)
	{
		roomRequests.offer((AbstractRoomRequest) (room, serverConnection) -> addPlayer(playerName, playerID, connection));
	}

	final void enqueueRemoval(final int playerID)
	{
		roomRequests.offer((AbstractRoomRequest) (room, serverConnection) -> removePlayer(playerID));
	}

	/**
	 * creates a new player inside this room, if such player does no exist. Triggers a playerJoinEvent.
	 *
	 * @param playerName the name of the new player.
	 * @param playerID   the id of the new player.
	 */
	final void addPlayer(String playerName, int playerID, ServerConnection handler)
	{
		PlayerInfo info = new PlayerInfo(playerName, playerID, handler);
		players.put(playerID, info);
		notifyChange();
		if (getSyncOgg() != null)
			handler.onRoomChanged(getSyncOgg());
		LOGGER.fine("Player added to room");
	}

	/**
	 * remove a player from this room. Trigger a playerLeaveEvent if such player existed.
	 *
	 * @param playerID
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
	final PlayerInfo getPlayerOfChair(int chairID)
	{
		if (chairID < 0)
			return null;

		for (PlayerInfo f : players.values())
			if (f.getChairID() == chairID)
				return f;

		return null;
	}

	final int getPlayerCount()
	{
		return players.keySet().size();
	}

	RoomView getView()
	{
		RoomView v = new RoomView(name, id);
		for (PlayerInfo info : players.values())
			v.addPlayer(info);

		return v;
	}

	void stop()
	{
		isAlive = false;
	}

	private final void tearDown()
	{
		isAlive = false;
		for (Map.Entry<Integer, PlayerInfo> p : players.entrySet())
		{
			p.getValue().getHandler().disconnect();
			removePlayer(p.getKey());
		}
		onTearDown();
	}

	void broadcast(String message)
	{
		for (int p : players.keySet())
			sendMessage(p, message);
	}

	void notifyChange()
	{
		for (PlayerInfo p : players.values())
			p.getHandler().onRoomModified(this);
	}

	abstract void processCommand(String syncString, int callerID);

	protected void onTearDown(){}

	void sendMessage(int targetID, String message)
	{
		if (players.get(targetID) != null)
			players.get(targetID).getHandler().sendMessage(message);
	}
}
