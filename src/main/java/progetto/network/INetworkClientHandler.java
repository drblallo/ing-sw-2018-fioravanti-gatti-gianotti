package progetto.network;

import progetto.utils.Callback;

public interface INetworkClientHandler {

	void disconnect(boolean disconectGracefully);

	void sendMessage(String message);
	void sendEnforce(AbstractEnforce enforce);

	/**
	 *
	 * @return the callaback that is called every time a message is received
	 */
	Callback<String> getMessageCallback();

	/**
	 *
	 * @return the callback that is called every time a connection is lost
	 */
	Callback<INetworkClientHandler> getConnectionLostCallback();

	Callback<AbstractRequest> getRequestCallback();
}
