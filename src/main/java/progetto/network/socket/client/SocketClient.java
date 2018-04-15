package progetto.network.socket.client;

import progetto.network.INetworkClient;
import progetto.network.ISync;
import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.network.socket.AbstractSocketManager;
import progetto.network.socket.server.*;
import progetto.utils.Callback;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A socket client is a implementation of INetwork client, it is used on the client side to communicate with the server
 */
public final class SocketClient extends AbstractSocketManager implements INetworkClient {

	private static final Logger LOGGER = Logger.getLogger( SocketClient.class.getName() );
	private ServerState serverState = new ServerState();
	private int playerID = -1;
	private Room roomInfo = new Room();
	private final Callback<INetworkClient> syncronizationFailedCallback = new Callback<INetworkClient>();
	private final Callback<INetworkClient> connectionLostCallback = new Callback<INetworkClient>();
	private final ISync synchronizedObj;

	public SocketClient(ISync ogg, String ip, int port)
	{
		super(ip, port);
		synchronizedObj = ogg;
	}

	/**
	 *
	 * @return the synchronization object currently used
	 */
	public final ISync getSynchronizedObject()
	{
		return synchronizedObj;
	}

	/**
	 *	sets the server state to the provided state.
	 *	used by ServerStateTransferCommand to provided the client with rooms info.
	 * @param state new state of the server
	 */
	final void setServerState(ServerState state)
	{
		serverState = state;
		LOGGER.log(Level.INFO, "Received server state information");
	}

	/**
	 * @return the server state fetched by the server
	 */
	public final ServerState getServerState() {
		return serverState;
	}

	/**
	 * tries to create a room on the server
	 * @param roomName name of the new room
	 */
	public final void createGame(String roomName)
	{
		LOGGER.log(Level.INFO, "Trying to create a room");
		sendCommand(new CreateRoomCommand(roomName));
	}

	/**
	 * callback called every time a discrepancy in synchronization is noted
	 * @return the instance of socket client that failed the synchronization
	 */
	public final Callback<INetworkClient> getSyncronizationFailedCallback() {
		return syncronizationFailedCallback;
	}

	/**
	 * return the callback that is called every time the connections is closed.
	 * @return the instance of the client that lost connection
	 */
	public final Callback<INetworkClient> getConnectionLostCallback()
	{
		return connectionLostCallback;
	}

	/**
	 *	ask the server for the current state of the server
	 */
	public void fetchServerState() {
		sendCommand(new FetchServerStateCommand());
	}

	public void sendSyncCommand(String command) {
		LOGGER.log(Level.INFO,"Sending sync command to server {0}", command);
		sendCommand(new SendSynchString(command));
	}

	public int getPlayerID()
	{
		return playerID;
	}

	/**
	 * called when the connection gets closed.
	 */
	protected final void onTearDown()
	{
		connectionLostCallback.call(this);
	}

	/**
	 * Tries to join a game
	 * @param roomID roomID you are trying to join
	 * @param playerName the name with which you will be called.
	 */
	public final void joinGame(int roomID, String playerName)
	{
		LOGGER.info("Trying to join room");
		sendCommand(new JoinRoomCommand(roomID, playerName));
	}

	/**
	 * @return the current room
	 */
	public final Room getRoom()
	{
		return roomInfo;
	}

	/**
	 * tries to select a chair for this player
	 * @param chairID the number of the chair you are trying to pick
	 */
	public final void pickChair(int chairID) {
		sendCommand(new PickChairCommand(chairID));
	}

	/**
	 * tries to send a message to a particular player
	 * @param message message you are trying to send
	 * @param targetID target you are trying to reach.
	 */
	public final void sendPrivateMessage(String message, int targetID)
	{
		sendCommand(new PrivateMessageCommand(getPlayerName(), message, targetID));
	}

	/**
	 *
	 * @return the name of the current player.
	 */
	public final String getPlayerName()
	{
		if (roomInfo.getInfoFromID(playerID) == null)
			return "No Name: (are you not in a room?)";

		return roomInfo.getInfoFromID(playerID).getPlayerName();
	}

	/**
	 * tries to set this player ready
	 * @param ready new state of the ready value
	 */
	public final void setReady(boolean ready) {
		sendCommand(new SetReadyCommand(ready));
	}

	/**
	 * set the id of the current player
	 * @param plID new value of the player id
	 */
	final void setPlayerID(int plID)
	{
		LOGGER.log(Level.INFO,"Received a player ID from network: {0}", plID);
		playerID = plID;
	}

	/**
	 * update the room info with the new one
	 * @param r
	 */
	final void setRoomInfo(Room r)
	{
		if (r != null)
			roomInfo.updateToNewInfo(r);
		else
			roomInfo.updateToNewInfo(new Room());
	}

	/**
	 * sends a string to the syncObject.
	 * @param s
	 */
	final void processSyncCommand(String s)
	{
		getSynchronizedObject().sendString(s);
	}

	/**
	 * checks if the state of the object is correct
	 * @param state index of hash that must be compared
	 * @param hash hash received by the server
	 */
	final void checkSync(int state, String hash)
	{
		ISync ogg = getSynchronizedObject();
		if (!ogg.getHash(state).equals(hash))
		{
			LOGGER.log(Level.SEVERE, "SYNCHRONIZATION FAILED");
			syncronizationFailedCallback.call(this);
		}

	}

	/**
	 * reload the entire state of the syncObject
	 * @param commands new state
	 */
	final void setFullState(ArrayList<String> commands)
	{
		LOGGER.info("Receiving full state from server");
		getSynchronizedObject().clear();
		for (String s : commands)
			getSynchronizedObject().sendString(s);
	}

	public int getChair()
	{
		if (roomInfo.getInfoFromID(playerID) == null)
			return -1;
		return roomInfo.getInfoFromID(playerID).getChairID();
	}

	public boolean isReady()
	{
		if (roomInfo.getInfoFromID(playerID) == null)
			return false;
		return roomInfo.getInfoFromID(playerID).isReady();
	}
}
