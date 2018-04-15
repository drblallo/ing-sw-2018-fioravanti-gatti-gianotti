package progetto.network.connectionstate;

import progetto.utils.Callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Room contains all the information needed to keep track of the players inside a room.
 * Inside a room players name are unique.
 */
public final class Room implements Serializable{

	private String name;
	private int id;
	private List<PlayerInfo> players = Collections.synchronizedList(new ArrayList<PlayerInfo>());
	private final transient Callback<String> nameChangedEvent = new Callback<String>();
	private final transient Callback<Integer> playerJoinEvent = new Callback<Integer>();
	private final transient Callback<Integer> playerLeaveEvent = new Callback<Integer>();
	private final transient PlayerInfoObserver obs = new PlayerInfoObserver(this);
	private final transient Callback<Room> changeEvent = new Callback<Room>();

	private static final Logger LOGGER = Logger.getLogger(Room.class.getName());

	/**
	 * Builds a room detached from a ServerState. id will be -1.
	 */
	public Room()
	{
		id = -1;
		name = "NO NAME";
	}

	/**
	 * Builds a room with specified name and id
	 */
	Room(String roomName, int roomID)
	{
		id = roomID;
		name = roomName;
	}

	/**
	 *
	 * @return the current name of this room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Change the name of this room, nomeChangedEvent will be called with the old name as argument.
	 * @param roomName
	 */
	public void setName(String roomName)
	{
		LOGGER.log(Level.FINE,"changed room name to: {0}", roomName);
		String oldName = name;
		name = roomName;
		nameChangedEvent.call(oldName);
		changeEvent.call(this);
	}

	/**
	 * @return the ID of this room, this value is unique inside a server.
	 */
	public int getRoomID() {
		return id;
	}

	/**
	 * set the state of this room to be equal to the one of the room provided.
	 * All callbacks will be called.
	 * @param newState the new state.
	 */
	public synchronized void updateToNewInfo(Room newState)
	{
		LOGGER.log(Level.FINE, "updating room info");
		changeEvent.stop();
		for (int a = players.size() - 1; a >= 0; a--) //remove missing players
		{
			PlayerInfo info = newState.getInfoFromID(players.get(a).getPlayerID());
			if (info == null)
				removePlayer(players.get(a).getPlayerID());
		}

		for (PlayerInfo p : newState.players) {
			PlayerInfo info = getInfoFromID(p.getPlayerID());
			if (info == null) {
				addPlayer(p.getPlayerName(), p.getPlayerID()); //add new player
				info = getInfoFromID(p.getPlayerID());
			}

			if (info != null)
				info.setInfo(p); //set player information
		}
		changeEvent.start();
		id = newState.id;
		setName(newState.getName());
	}

	/**
	 * returns the playerinfo related to the player inside this room.
	 * @param id the id of the player.
	 * @return null if there is no such player, its information otherwise.
	 */
	public synchronized PlayerInfo getInfoFromID(int id)
	{
			for (PlayerInfo i : players)
				if (i.getPlayerID() == id)
					return i;
			return null;
	}

	/**
	 *
	 * @return a arraylist holding all the playerInfo of the players inside this room.
	 */
	public synchronized List<PlayerInfo> getPlayers()
	{
		ArrayList<PlayerInfo> pls = new ArrayList<PlayerInfo>();
		pls.addAll(players);
		return pls;
	}

	/**
	 * creates a new player inside this room, if such player does no exist. Triggers a playerJoinEvent.
	 * @param playerName the name of the new player.
	 * @param playerID the id of the new player.
	 */
	synchronized void addPlayer(String playerName, int playerID)
	{
		PlayerInfo info = new PlayerInfo(playerName, playerID);
		players.add(info);
		LOGGER.info("Player added to room");
		info.getChangedEvent().addObserver(obs);
		playerJoinEvent.call(playerID);
		changeEvent.call(this);
	}

	/**
	 * remove a player from this room. Trigger a playerLeaveEvent if such player existed.
	 * @param playerID
	 */
	synchronized void removePlayer(int playerID)
	{
		PlayerInfo info = getInfoFromID(playerID);
		if (info == null)
			return;

		LOGGER.info("Player removed from room");
		players.remove(info);
		info.getChangedEvent().removeObserver(obs);
		playerLeaveEvent.call(info.getPlayerID());
		changeEvent.call(this);
	}

	/**
	 *
	 * @return returns the callback that is triggered when this room name changes
	 */
	public Callback<String> getNameChangedEvent() {
		return nameChangedEvent;
	}

	/**
	 *
	 * @return the callback that is triggered when a player joins this room.
	 */
	public Callback<Integer> getPlayerJoinEvent() {
		return playerJoinEvent;
	}

	/**
	 *
	 * @return the callback that is triggered when a player leaves this room.
	 */
	public Callback<Integer> getPlayerLeaveEvent() {
		return playerLeaveEvent;
	}

	/**
	 *
	 * @return the callback that is called when something inside this room changes
	 */
	public Callback<Room> getChangeEvent() {
		return changeEvent;
	}

	/**
	 * returns the player that is inside a particular chair
	 * @param chairID a number between 0 and MAX_CHAIR_NUMBER
	 * @return the player that is sitting in a particular chair, null if chairID is invalid or if nobody is sitting
	 * in that chair.
	 */
	public synchronized PlayerInfo getPlayerOfChair(int chairID)
	{
		if (chairID < 0)
			return null;

		for (PlayerInfo f : players)
			if (f.getChairID() == chairID)
				return f;

		return null;
	}

	/**
	 * Sets the chair of a particular player, if that chair is already taken then nothing is done.
	 * @param playerID
	 * @param newChairID
	 */
	public synchronized void setPlayerChair(int playerID, int newChairID)
	{
		PlayerInfo info = getInfoFromID(playerID);
		if (info != null && (getPlayerOfChair(newChairID) == null || newChairID == -1)) {
			info.setChairID(newChairID);
			LOGGER.log(Level.FINE,"Player took {0} chair", newChairID);
		}
	}

	public synchronized Room deepCopy()
	{
		Room r = new Room();
		r.setName(getName());
		r.id = getRoomID();
		for (PlayerInfo p : players)
			r.players.add(p.deepCopy());
		return r;
	}
}
