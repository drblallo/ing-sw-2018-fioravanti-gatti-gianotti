package progetto.network;

public interface IRoom {

	String getName();
	int getChairOfPlayer(String playerName);
	boolean isPlayerReady(String playerName);
	boolean isPlayerObserver(String playerName);
	String getPlayerOfChair(int chairID);
}
