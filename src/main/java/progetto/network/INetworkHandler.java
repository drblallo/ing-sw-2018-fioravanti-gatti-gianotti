package progetto.network;

import progetto.utils.Callback;

/**
 * The server side implementation of a message transport layer must implement this interface.
 * @author Massimo
 */
public interface INetworkHandler {

	/**
	 * shuts down the connection
	 * @param disconnectGracefully true if the connection must be closed gracefully.
	 */
	void disconnect(boolean disconnectGracefully);

	/**
	 * send a string to the other side. the string can be received out of order.
	 * @param message the string to be sent
	 */
	void sendMessage(String message);

	/**
	 * send an enforce to the other side. the enforce must be given to the higher level in order.
	 * @param enforce the enforce to be sent
	 */
	void sendEnforce(IEnforce enforce);

	/**
	 *
	 * @return if the underlying implementation is still running.
	 */
	boolean isRunning();

	/**
	 *
	 * @return the callback that is called every time a request is received
	 */
	Callback<IRoomRequest> getRequestCallback();
}
