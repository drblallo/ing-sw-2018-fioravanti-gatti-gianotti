package progetto.network;

import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
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
	 * @param roomName tries to create a room on the server
	 */
	void createGame(String roomName);

	/**
	 *  tries to join a room
	 * @param playerName the name with which you will be called.
	 */
	void joinGame(int roomID, String playerName);

	/**
	 *
	 * @return the current room
	 */
	Room getRoom();

	/**
	 * tries to pick a particular chair
	 * @param chairID
	 */
	void pickChair(int chairID);

	/**
	 * send a message to a particular player
	 * @param message
	 * @param targetID
	 */
	void sendPrivateMessage(String message, int targetID);

	/**
	 * set the player ready to start.
	 * @param ready
	 */
	void setReady(boolean ready);

	/**
	 *
	 * @return the callaback that is called every time a message is received
	 */
	Callback<String> getMessageCallback();

	/**
	 *
	 * @return the callback that is called every time a connection is lost
	 */
	Callback<INetworkClient> getConnectionLostCallback();

	/**
	 * ask the server for a new copy of the server state
	 */
	void fetchServerState();

	/**
	 * send a action that must be recieved by every player
	 * @param command
	 */
	void sendSyncCommand(String command);

	/**
	 * return the last received server state
	 * @return
	 */
	ServerState getServerState();

	int getChair();

	int getPlayerID();

	boolean isReady();
}
