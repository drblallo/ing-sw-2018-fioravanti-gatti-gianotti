package progetto.network;

public interface INetworkServer {
	void start();
	void stop();

	int getRoomCount();
	IRoom getRoom(String roomName);

}
