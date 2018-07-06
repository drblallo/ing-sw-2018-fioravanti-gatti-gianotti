package progetto.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Massimo
 */
public final class RoomView implements Serializable{
	private final Map<Integer, PlayerView> playerInfoList = new HashMap<>();
	private final String roomName;
	private final int roomID;
	private static final int MAX_DISPLAYED_CHAIRS = 4;

	/**
	 * creates a new instance
	 * @param roomName the name of the new room
	 * @param roomID the id of the new room
	 */
	public RoomView(String roomName, int roomID) {
		this.roomName = roomName;
		this.roomID = roomID;
	}

	/**
	 * add a player to the room
	 * @param info the player to be added to the room
	 */
	void addPlayer(Player info)
	{
		playerInfoList.put(info.getPlayerID(), info.getView());
	}

	/**
	 * @param playerID the id of the player to be retrieved
	 * @return the player, null if it does not exists
	 */
	public PlayerView getPlayer(int playerID)
	{
		return playerInfoList.get(playerID);
	}

	/**
	 *
	 * @return the name of this room
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 *
	 * @return the id of this room
	 */
	public int getRoomID() {
		return roomID;
	}

	/**
	 *
	 * @return the player count
	 */
	public int getPlayerCount()
	{
		return playerInfoList.size();
	}

	/**
	 *
	 * @param playerChair the chair of the player that you wish to retrieve
	 * @return the player sitting in that chair, if it exists
	 */
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
