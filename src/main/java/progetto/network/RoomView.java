package progetto.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public final class RoomView implements Serializable{
	private final Map<Integer, PlayerView> playerInfoList = new HashMap<>();
	private final String roomName;
	private final int roomID;

	public RoomView(String roomName, int roomID) {
		this.roomName = roomName;
		this.roomID = roomID;
	}

	void addPlayer(Player info)
	{
		playerInfoList.put(info.getPlayerID(), info.getView());
	}

	public PlayerView getPlayer(int playerID)
	{
		return playerInfoList.get(playerID);
	}

	public String getRoomName() {
		return roomName;
	}

	public int getRoomID() {
		return roomID;
	}

	public int getPlayerCount()
	{
		return playerInfoList.size();
	}

	public PlayerView getPlayerOfChair(int playerChair)
	{
		if (playerChair <= 0)
			return null;

		for (PlayerView pl : playerInfoList.values())
			if (pl.getChairID() == playerChair)
				return pl;
		return null;
	}
}
