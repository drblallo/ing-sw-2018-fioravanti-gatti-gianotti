package progetto.network.connectionstate;

import progetto.utils.Callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class holds all the information at room level that are need to the server.
 *
 * A server is composed by a list of room. Each room holds players.
 */
public final class ServerState implements Serializable{
	private List<Room> rooms = Collections.synchronizedList(new ArrayList<Room>());
	private int lastID = 0;
	private int lastPlayerID = 0;
	private transient Callback<Room> createRoomEvent = new Callback<Room>();
	private transient Callback<Integer> playerChangedRoom = new Callback<Integer>();

	/**
	 *
	 * @return the count of rooms inside this server
	 */
	public int getRoomCount() {
		return rooms.size();
	}

	/**
	 *
	 * @return a arraylist holding all the rooms
	 */
	public synchronized List<Room> getRooms()
	{
		ArrayList<Room> toBeReturned = new ArrayList<Room>();
		toBeReturned.addAll(rooms);
		return toBeReturned;
	}

	/**
	 *
	 * creates a room with provided name, rooms with the same name are allowed
	 * @param roomName the name of the new room
	 * @return the created room
	 */
	public synchronized Room createRoom(String roomName)
	{
		Room r = new Room(roomName, lastID);
		lastID++;
		rooms.add(r);
		createRoomEvent.call(r);
		return r;
	}

	/**
	 *
	 * @return the callback that is called when a room is created
	 */
	public Callback<Room> getCreateRoomEvent() {
		return createRoomEvent;
	}

	/**
	 *
	 * @return the callback that is called when a player enters or leaves a room
	 */
	public Callback<Integer> getPlayerChangedRoom() {
		return playerChangedRoom;
	}

	/**
	 *
	 * @return returns the room with that id.
	 */
	public synchronized Room getRoom(int roomID)
	{
		for (Room r : rooms)
			if (r.getRoomID() == roomID)
				return r;
		return null;
	}


	/**
	 *
	 * @return returns true if there is a player with such id
	 */
	public synchronized boolean playerExists(int id)
	{
		for (Room r : rooms)
		{
			PlayerInfo i = r.getInfoFromID(id);
			if (i != null)
				return true;
		}
		return false;
	}

	/**
	 *
	 * @return returns the room of the player with such id, false if such player does not exists.
	 */
	public synchronized Room getRoomOfPlayer(int id)
	{
		for (Room r : rooms)
		{
			if (r.getInfoFromID(id) != null)
				return r;
		}
		return null;
	}

	/**
	 * sets a player in a specified room
	 * @param playerID the id of the new player
	 * @param roomID the room where it should be spawned, if roomID == -1 then the player removed from his room
	 */
	public synchronized void placePlayer(int playerID, String newName, int roomID)
	{
		Room oldRoom = getRoomOfPlayer(playerID);
		if (oldRoom != null)
			oldRoom.removePlayer(playerID);

		if (roomID != -1)
		{
			Room r = getRoom(roomID);
			if (r != null)
				r.addPlayer(newName, playerID);

		}
		playerChangedRoom.call(playerID);
	}

	/**
	 * returns an unused id inside this server
	 * @return
	 */
	public synchronized int getUnusedID()
	{
		return lastPlayerID++;
	}

	public synchronized PlayerInfo getPlayer(int playerID)
	{
		for (Room r : rooms) {
			if (r.getInfoFromID(playerID) != null)
				return r.getInfoFromID(playerID);
		}
		return null;
	}

	public synchronized ServerState deepCopy()
	{
		ServerState state = new ServerState();
		state.lastPlayerID = lastPlayerID;
		state.lastID  = lastID;
		for (Room r : rooms)
			state.rooms.add(r.deepCopy());
		return state;
	}

}
