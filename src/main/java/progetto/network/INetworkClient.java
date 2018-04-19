package progetto.network;

import progetto.utils.Callback;

public interface INetworkClient {

	/**
	 * Shutdown the connection
	 *
	 * @param signalGoodBye true the connection must be closed gracefully.
	 */
	void disconnect(boolean signalGoodBye);

	/**
	 * @return true if the connection is still open
	 */
	boolean isRunning();

	/**
	 * @return the callaback that is called every time a message is received.
	 */
	Callback<String> getMessageCallback();

	/**
	 *
	 * @return the callback that is called every time a enforce is received by the network.
	 * Implementer must ensure that enforcers are received in order.
	 */
	Callback<IEnforce> getEnforceCallback();

	/**
	 *
	 * @param request request to be sent, ensures that it will be performed after the requests already sent.
	 */
	void sendRequest(AbstractRoomRequest request);

}
