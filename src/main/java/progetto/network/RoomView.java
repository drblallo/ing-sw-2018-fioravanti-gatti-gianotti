package progetto.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RoomView implements Serializable{
	private final Map<Integer, PlayerView> playerInfoList = new HashMap<>();
	private final String roomName;
	private final int roomID;
	private static final int MAX_DISPLAYED_CHAIRS = 4;

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
		if (playerChair < 0)
			return null;

		for (PlayerView pl : playerInfoList.values())
			if (pl.getChairID() == playerChair)
				return pl;
		return null;
	}


	/**
	 *
	 * @return the content of this object as a list
	 */
	public List<PlayerView> asList(){
		ArrayList<PlayerView> playerViewArrayList = new ArrayList<>();
		for(PlayerView r : playerInfoList.values()){
			playerViewArrayList.add(r);
		}
		return playerViewArrayList;
	}

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nE' cambiata la disposizione dei giocatori sulle sedie!\nNuova disposizione:\n");
		for(int i =0; i< MAX_DISPLAYED_CHAIRS; i++ ){
			PlayerView p = getPlayerOfChair(i);
			if(p  != null)
			{
				stringBuilder.append("Sedia ").append(i).append(": ").append(p.getName()).append('\n');
			}
			else
				stringBuilder.append("Sedia ").append(i).append(": LIBERA").append('\n');
		}
		return stringBuilder.toString();
	}
}
