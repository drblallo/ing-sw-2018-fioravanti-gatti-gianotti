package progetto.network;

import progetto.utils.Callback;

/**
 * @author Massimo
 */
public interface INetworkModule {
	/**
	 * shutdows the module, disconnect every connection
	 */
	void stop();

	/**
	 * starts the module
	 */
	void start();

	/**
	 * @return true if it's running
	 */
	boolean isRunning();


	Callback<INetworkHandler> getPlayerJoinedCallback();
}
