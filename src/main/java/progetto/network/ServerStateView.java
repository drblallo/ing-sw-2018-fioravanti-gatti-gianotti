package progetto.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class ServerStateView implements Serializable
{
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

	void addRoom(String roomName, int roomID, int playerSize)
	{
		rooms.put(roomID, new SimpleRoomState(roomName, roomID, playerSize));
	}

	public SimpleRoomState getRoom(int roomID)
	{
		return rooms.get(roomID);
	}

	public SimpleRoomState getRoomFromName(String gameName)
	{
		for (SimpleRoomState r : rooms.values())
			if (r.roomName.equals(gameName))
				return r;
		return null;
	}

	public int getRoomCount()
	{
		return rooms.size();
	}
}
