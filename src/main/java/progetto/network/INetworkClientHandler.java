package progetto.network;

import progetto.utils.Callback;

public interface INetworkClientHandler {

	void disconnect(boolean disconectGracefully);

	void sendMessage(String message);

	void sendEnforce(AbstractEnforce enforce);

	boolean isRunning();

	Callback<AbstractRoomRequest> getRequestCallback();
}
