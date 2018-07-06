package progetto;

import progetto.controller.IGameController;
import progetto.model.IModel;
import progetto.model.ObservableModel;
import progetto.network.RoomView;
import progetto.network.ServerStateView;
import progetto.utils.Callback;

/**
 * This is the main controller that contains all the references to every connection, every proxy model and every view.
 * This class hides the multiplicity of connections, so that every view can be attached to the same containers without
 * being aware of any change.
 */
public interface IClientController extends IGameController {

	/**
	 * set the current game. The current game is the one that will be observed
	 * @param index the index of the game that must be set
	 */
	void setCurrentClientGame(int index);


	/**
	 * @return the callback that is called every time a message is received by the current connection
	 */
	Callback<String> getMessageCallback();
	/**
	 *
	 * @return the callback that is called every time the roomview of the current connection is changed
	 */
	Callback<RoomView> getRoomViewCallback();
	/**
	 *
	 * @return the callback that is called every time the server state of the current connection is changed
	 */
	Callback<ServerStateView> getServerViewCallback();

	/**
	 *
	 * @return the the immutable model that was last received from the server
	 */
	IModel getModel();
	/**
	 *
	 * @return the model that is persistent but not thread safe. You can observer the various part of this if you wish to
	 * be notified every time they change. It does ensure that this model contains only between-actions data.
	 */
	ObservableModel getObservable();
	/**
	 * creates a new connection
	 * @param ip the adress of the server
	 * @param rmi true if you wish to create a rmi connection
	 * @return true if the connection was build correctly. False otherwise. Ensure that will not return
	 * until the connection is created
	 */
	boolean createConnection(String ip, boolean rmi);
	/**
	 * add a new view
	 * @param view the view to be added
	 */
	void addView(AbstractView view);
	/**
	 *
	 * @return the state of the current room
	 */
	RoomView getCurrentRoom();


	/**
	 *
	 * @return the state of the current server
	 */
	ServerStateView getCurrentServerState();
	/**
	 * Send a message to a player in the current room
	 * @param message the message to be sent
	 * @param targetID the id of the player that must receive the message
	 */
	void sendPrivateMessage(String message, int targetID);
	/**
	 * Tries to pick the suggested chair. The server can refuse such proposal
	 * @param chairID the id of the chair
	 */
	void pickChair(int chairID);
	/**
	 * Asks the server to resend the state information
	 */
	void fetchCurrentState();
	/**
	 * tries to create a new room on the server
	 * @param gameName the name of the game that must be created
	 */
	void createGame(String gameName);
	/**
	 * tries to join a game in the current connection
	 * @param roomID the id of the room that you wish to join
	 * @param playerName the name with which the player will be know in the room
	 */
	void joinGame(int roomID, String playerName);
	/**
	 * @param index the number of the connection
	 * @return the name of the connection
	 */
	String getNameOfConnection(int index);
	/**
	 *
	 * @return the current connection count
	 */
	int getConnectionCount();
	/**
	 * Tries to disconnect form the current server
	 */
	int getChair();
	/**
	 * Closes all connections, closes the process.
	 */
	void disconnect();
	/**
	 * Closes all connections, closes the process.
	 */
	void shutDown();

	/**
	 *
	 * @return true is there is a game
	 */
	boolean thereIsGame();
}

