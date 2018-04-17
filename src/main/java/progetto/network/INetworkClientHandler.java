package progetto.network;

import progetto.utils.Callback;

public interface INetworkClientHandler {

	void disconnect(boolean disconectGracefully);

	void sendMessage(String message);

	void sendEnforce(AbstractEnforce enforce);

	boolean isRunning();

	/**
	 * @return the callaback that is called every time a message is received
	 */
	Callback<String> getMessageCallback();


	Callback<AbstractRequest> getRequestCallback();
}
