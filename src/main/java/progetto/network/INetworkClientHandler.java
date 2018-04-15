package progetto.network;

import progetto.utils.Callback;

public interface INetworkClientHandler {

	void disconnect(boolean disconectGracefully);
	int getPlayerID();
	void setID(int id);

	void sendMessage(String message);
	void sendSyncCommand(String syncCommand);
	void checkSyncronization();
	void sendServerState();

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

}
