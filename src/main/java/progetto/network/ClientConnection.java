package progetto.network;

import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ClientConnection
{
	private final class ConnectionClosedObserver implements IObserver<INetworkClient>
	{
		private ClientConnection con;
		private ConnectionClosedObserver(ClientConnection c)
		{
			con = c;
		}


		public void notifyChange(INetworkClient ogg) {
			con.connectionEndedCallback.call(con);
		}
	}

	private static final Logger LOGGER = Logger.getLogger( ClientConnection.class.getName() );
	private ServerState serverState = new ServerState();
	private int playerID = -1;
	private Room roomInfo = new Room();
	private final ISync synchronizedObj;
	private final Callback<ClientConnection> syncronizationFailedCallback = new Callback<ClientConnection>();
	private final Callback<ClientConnection> connectionEndedCallback = new Callback<ClientConnection>();

	private final INetworkClient handler;

	public ClientConnection(INetworkClient h, ISync ogg)
	{
		handler = h;
		synchronizedObj = ogg;
		h.getEnforceCallback().addObserver(new EnforceObserver(this));
		h.getConnectionLostCallback().addObserver(new ConnectionClosedObserver(this));
	}

	/**
	 *
	 * @return the synchronization object currently used
	 */
	public final ISync getSynchronizedObject()
	{
		return synchronizedObj;
	}

	public Callback<ClientConnection> getConnectionEndedCallback() {
		return connectionEndedCallback;
	}

	/**
	 *	sets the server state to the provided state.
	 * @param state new state of the server
	 */
	final void setServerState(ServerState state)
	{
		serverState = state;
		LOGGER.log(Level.FINE, "Received server state information");
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
		LOGGER.log(Level.FINE, "Trying to create a room");

		handler.sendRequest(new RoomCreationRequest(roomName));
	}


	/**
	 * callback called every time a discrepancy in synchronization is noted
	 * @return the instance of socket client that failed the synchronization
	 */
	public final Callback<ClientConnection> getSyncronizationFailedCallback() {
		return syncronizationFailedCallback;
	}

	/**
	 *	ask the server for the current state of the server
	 */
	public void fetchServerState() {
		handler.sendRequest(new FetchServerStateRequest());
	}

	public int getPlayerID()
	{
		return playerID;
	}

	/**
	 * Tries to join a game
	 * @param roomID roomID you are trying to join
	 * @param playerName the name with which you will be called.
	 */
	public final void joinGame(int roomID, String playerName)
	{
		LOGGER.fine("Trying to join room");
		handler.sendRequest(new JoinRoomRequest(roomID, playerName));
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
		handler.sendRequest(new PickChairRequest(chairID));
	}

	/**
	 * tries to send a message to a particular player
	 * @param message message you are trying to send
	 * @param targetID target you are trying to reach.
	 */
	public final void sendPrivateMessage(String message, int targetID)
	{
		handler.sendRequest(new SendPrivateMessageRequest(getPlayerName()+": "+ message, targetID));
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
		handler.sendRequest(new SetReadyRequest(ready));
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
	final synchronized void processSyncCommand(String s)
	{
		getSynchronizedObject().sendString(s);
	}

	/**
	 * checks if the state of the object is correct
	 * @param state index of hash that must be compared
	 * @param hash hash received by the server
	 */
	final synchronized void checkSync(int state, String hash)
	{
		ISync ogg = getSynchronizedObject();
		if (!ogg.getHash(state).equals(hash))
		{
			LOGGER.log(Level.SEVERE, "SYNCHRONIZATION FAILED");
			syncronizationFailedCallback.call(this);
		}

	}

	public Callback<String> getMessageCallback()
	{
		return handler.getMessageCallback();
	}

	/**
	 * reload the entire state of the syncObject
	 * @param commands new state
	 */
	final synchronized void setFullState(List<String> commands)
	{
		LOGGER.fine("Receiving full state from server");
		getSynchronizedObject().clear();
		for (String s : commands)
			getSynchronizedObject().sendString(s);
	}

	/**
	 *
	 * @return the chair currently used by this chair, -1 if he is not in a room
	 */
	public int getChair()
	{
		if (roomInfo.getInfoFromID(playerID) == null)
			return -1;
		return roomInfo.getInfoFromID(playerID).getChairID();
	}

	/**
	 *
	 * @return the ready status of this player, false if he is not in a room
	 */
	public boolean isReady()
	{
		if (roomInfo.getInfoFromID(playerID) == null)
			return false;
		return roomInfo.getInfoFromID(playerID).isReady();
	}

	public void sendSynString(String s)
	{
		handler.sendRequest(new SendSyncStringRequest(s));
	}

	public void disconnect()
	{
		handler.disconnect(true);
	}

	public boolean isRunning()
	{
		return handler.isRunning();
	}


}
