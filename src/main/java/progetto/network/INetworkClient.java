package progetto.network;

import progetto.utils.Callback;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is the interface that must be implemented by a message transport layer to allow ClientConnection to behave
 * correctly
 */
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
	 * @return the callaback that is called every time a message is received. Messages can be recieved out of order and
	 * from every thread.
	 */
	Callback<String> getMessageCallback();

	/**
	 *
	 * @return the list of enforces
	 */
	ConcurrentLinkedQueue<IEnforce> getEnforceList();

	/**
	 *
	 * @param request request to be sent, implementation ensures that it will be sent
	 *               to the higher level only after the requests already sent.
	 */
	void sendRequest(IRoomRequest request);

}
