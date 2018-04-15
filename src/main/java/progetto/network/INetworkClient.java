package progetto.network;

import progetto.utils.Callback;

public interface INetworkClient {

	/**
	 * Shutdown the connection
	 * @param signalGoodBye true the connection must be closed gracefully.
	 */
	void disconnect(boolean signalGoodBye);

	/**
	 *
	 * @return true if the connection is still open
	 */
	boolean isRunning();

	/**
	 *
	 * @return the callaback that is called every time a message is received
	 */
	Callback<String> getMessageCallback();

	Callback<AbstractEnforce> getEnforceCallback();

	/**
	 *
	 * @return the callback that is called every time a connection is lost
	 */
	Callback<INetworkClient> getConnectionLostCallback();


	void sendRequest(AbstractRequest request);

}
