package progetto.network;

public interface INetworkClient {

	boolean connect(String ip, int portNumber, String playerName, String password);
	void disconect();

	void createGame(String roomName);
	void joinName(String roomName);
	String getRoomName();

	void pickChair(int chairID);
	void sendMessage(String s);
	void setPrivateMessage(String message, String targetPlayerName);
	void setReady(boolean ready);

	int getChairOfPlayer(String playerName);
	boolean isPlayerReady(String playerName);
	boolean isPlayerObserver(String playerName);
	String getPlayerOfChiar(int chairID);

	String getPlayerStat(String playerName, String stat);

}
