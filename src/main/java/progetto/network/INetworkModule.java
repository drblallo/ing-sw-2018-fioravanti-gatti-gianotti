package progetto.network;

import progetto.utils.Callback;

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


	Callback<INetworkClientHandler> getPlayerJoinedCallback();
}
