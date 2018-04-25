package progetto.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A representation of the server state that can be sent across the network.
 */
public final class ServerStateView implements Serializable
{
	/**
	 * A simple struct that holds room name, room id and player count
	 */
	public final class SimpleRoomState implements Serializable
	{
		public final String roomName;
		public final int roomID;
		public final int playerSize;

		public SimpleRoomState(String roomName, int roomID, int playerSize)
		{
			this.roomName = roomName;
			this.roomID = roomID;
			this.playerSize = playerSize;
		}
	}

	private final Map<Integer, SimpleRoomState> rooms = new HashMap<>();

	/**
	 * adds information about a room.
	 * @param roomName room name
	 * @param roomID room id
	 * @param playerSize player count
	 */
	void addRoom(String roomName, int roomID, int playerSize)
	{
		rooms.put(roomID, new SimpleRoomState(roomName, roomID, playerSize));
	}

	/**
	 * return the room state of a particular room
	 * @param roomID the room requested
	 * @return the room info if such room exists, null otherwise.
	 */
	public SimpleRoomState getRoom(int roomID)
	{
		return rooms.get(roomID);
	}

	/**
	 * Return a room state
	 * @param gameName the name of the room requested
	 * @return the first instance of a room with that name if such room exists, null otherwise.
	 */
	public SimpleRoomState getRoomFromName(String gameName)
	{
		for (SimpleRoomState r : rooms.values())
			if (r.roomName.equals(gameName))
				return r;
		return null;
	}

	/**
	 *
	 * @return the count of room in thi server state
	 */
	public int getRoomCount()
	{
		return rooms.size();
	}
}
