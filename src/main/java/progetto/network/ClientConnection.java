package progetto.network;

import progetto.network.proxy.ModelProxy;
import progetto.utils.Callback;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client connection is the object encapsulating the the INetworkClient implementations that provide to the client
 * all the method needed to communicate with the server.
 *
 * All request sending methods are synchronized, so requests can be performed from any thread.
 */
public final class ClientConnection implements Runnable
{

	private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());
	private RoomView roomInfo = new RoomView("No Name", -1);
	private final ISync synchronizedObj;
	private final Callback<ClientConnection> syncronizationFailedCallback = new Callback<>();

	private final Callback<ServerStateView> serverStateViewCallback = new Callback<>();
	private final Callback<RoomView> roomViewCallback = new Callback<>();
	private final Callback<ModelProxy> actionEndedCallback = new Callback<>();
	private final Callback<ClientConnection> connectionClosedCallback = new Callback<>();

	private final INetworkClient handler;
	private ServerStateView serverState = new ServerStateView();
	private int playerID = -1;
	private ModelProxy proxy = new ModelProxy();
	private ModelProxy lastClone = new ModelProxy();

	/**
	 * creates a client connection. This will start the thread that processes the pending enforces sent by the server
	 * @param h the INetworkClient implementation that must be used to exchange message with the server.
	 * @param ogg the synchronization object that will be used in this session. The object will be cleared.
	 */
	public ClientConnection(INetworkClient h, ISync ogg)
	{
		handler = h;
		synchronizedObj = ogg;

		Thread t = new Thread(this);
		t.setName("Client connection");
		t.start();
	}

	public ModelProxy getProxy()
	{
		return proxy;
	}

	public void onActionResolved()
	{
		lastClone = proxy.copy();
		actionEndedCallback.call(lastClone);
	}

	public ModelProxy getLastClone() {
		return lastClone;
	}

	public Callback<ModelProxy> getActionEndedCallback() {
		return actionEndedCallback;
	}

	public Callback<ServerStateView> getServerStateViewCallback() {
		return serverStateViewCallback;
	}

	public Callback<RoomView> getRoomViewCallback() {
		return roomViewCallback;
	}

	public Callback<ClientConnection> getConnectionClosedCallback() {
		return connectionClosedCallback;
	}

	/**
	 * @return the synchronization object currently used
	 */
	public final ISync getSynchronizedObject() {
		return synchronizedObj;
	}

	/**
	 * @return the server state fetched by the server
	 */
	public final ServerStateView getServerState() {
		return serverState;
	}

	/**
	 * sets the server state to the provided state.
	 *
	 * @param state new state of the server
	 */
	final void setServerState(ServerStateView state) {
		serverState = state;
		LOGGER.log(Level.FINE, "Received server state information");
		serverStateViewCallback.call(state);
	}

	/**
	 * tries to create a room on the server
	 *
	 * @param roomName name of the new room
	 */
	public final synchronized void createGame(String roomName) {
		LOGGER.log(Level.FINE, "Trying to create a room");

		sendServerRequest(new RoomCreationRequest(roomName));
	}


	/**
	 * callback called every time a discrepancy in synchronization is noted
	 *
	 * @return the instance of socket client that failed the synchronization
	 */
	public final synchronized Callback<ClientConnection> getSyncronizationFailedCallback()
	{
		return syncronizationFailedCallback;
	}

	/**
	 * ask the server for the current state of the server
	 */
	public synchronized void fetchServerState()
	{
		sendServerRequest(new FetchServerStateRequest());
	}

	/**
	 *
	 * @return the id of the player. Player id cannot change at run time, but can be equal to -1 if the server
	 * did not yet sent the value that will be used this model
	 */
	public synchronized int getPlayerID()
	{
		return playerID;
	}

	/**
	 * set the id of the current player
	 *
	 * @param plID new value of the player id
	 */
	final synchronized void setPlayerID(int plID) {
		LOGGER.log(Level.FINE, "Received a player ID from network: {0}", plID);
		playerID = plID;
	}

	/**
	 * Tries to join a model
	 *
	 * @param roomID     roomID you are trying to join
	 * @param playerName the name with which you will be called.
	 */
	public final synchronized void joinGame(int roomID, String playerName) {
		LOGGER.fine("Trying to join room");
		sendServerRequest(new JoinRoomRequest(roomID, playerName));
	}

	/**
	 * sends an abstract request to the server
	 * @param req the request to be sent
	 */
	private synchronized void sendServerRequest(AbstractServerRequest req)
	{
		handler.sendRequest(new EncapsulationRoomRequest(req));
	}

	/**
	 * @return the current room
	 */
	public final RoomView getRoom() {
		return roomInfo;
	}

	/**
	 * tries to select a chair for this player
	 *
	 * @param chairID the number of the chair you are trying to pick
	 */
	public final synchronized void pickChair(int chairID) {
		handler.sendRequest(new PickChairRoomRequest(chairID));
	}

	/**
	 * tries to send a message to a particular player
	 *
	 * @param message  message you are trying to send
	 * @param targetID target you are trying to reach.
	 */
	public final synchronized void sendPrivateMessage(String message, int targetID) {
		handler.sendRequest(new SendPrivateMessageRoomRequest(getPlayerName() + ": " + message, targetID));
	}

	/**
	 * @return the name of the current player.
	 */
	public final synchronized String getPlayerName() {
		if (roomInfo.getPlayer(playerID) == null)
			return "No Name: (are you not in a room?)";

		return roomInfo.getPlayer(playerID).getName();
	}

	/**
	 * update the room info with the new one
	 *
	 * @param r the new room view
	 */
	final synchronized void setRoomInfo(RoomView r)
	{
		LOGGER.log(Level.FINE, "received new room info ");
		roomInfo = r;
		roomViewCallback.call(r);
	}

	/**
	 * sends a string to the syncObject.
	 *
	 * @param s the string that must be sent to the sync object
	 */
	final synchronized void processSyncCommand(Serializable s) {
		getSynchronizedObject().sendItem(s);
	}

	/**
	 * checks if the state of the object is correct
	 *
	 * @param state index of hash that must be compared
	 * @param hash  hash received by the server
	 */
	final synchronized void checkSync(int state, int hash) {
		ISync ogg = getSynchronizedObject();
		if (ogg.getHash(state) != hash) {
			LOGGER.log(Level.SEVERE, "SYNCHRONIZATION FAILED");
			syncronizationFailedCallback.call(this);
		}

	}

	/**
	 * @return the callback that is called when a message is received by the network. Notice that
	 * messages are not processed from the enforce processing thread, therefore if can be triggered at any time.
	 */
	public Callback<String> getMessageCallback() {
		return handler.getMessageCallback();
	}

	/**
	 * reload the entire state of the syncObject
	 *
	 * @param commands new state
	 */
	final synchronized void setFullState(List<Serializable> commands) {
		LOGGER.fine("Receiving full state from server");
		getSynchronizedObject().clear();
		for (Serializable s : commands)
			getSynchronizedObject().sendItem(s);
	}

	/**
	 * @return the chair currently used by this chair, -1 if he is not in a room
	 */
	public synchronized int getChair() {

		PlayerView info = roomInfo.getPlayer(playerID);
		if (info == null) {
			LOGGER.log(Level.FINE, "did not found player {0}", playerID);
			return -1;
		}
		LOGGER.log(Level.FINE, "found chair: {0}", info.getChairID());
		return info.getChairID();
	}

	/**
	 * @return the ready status of this player, false if he is not in a room
	 */
	public synchronized boolean isReady() {
		PlayerView v = roomInfo.getPlayer(playerID);
		return (v != null && v.isReady());
	}

	/**
	 * tries to set this player ready
	 *
	 * @param ready new state of the ready value
	 */
	public final synchronized void setReady(boolean ready) {
		handler.sendRequest(new SetReadyRoomRequest(ready));
	}

	/**
	 * send a sync string
	 * @param s the sync string to be sent
	 */
	public synchronized void sendSynString(Serializable s) {
		handler.sendRequest(new SendSyncStringRoomRequest(s));
	}

	/**
	 * shuts down the connection, tries to disconnect gracefully.
	 */
	public synchronized void disconnect() {
		handler.disconnect(true);
	}

	/**
	 *
	 * @return true if the underlying implementation is still running
	 */
	public synchronized boolean isRunning() {
		return handler.isRunning();
	}

	/**
	 * used by the command process thread to process al pending requests
	 */
	private synchronized void processAllCommand()
	{
		IEnforce f;
		while ((f = handler.getEnforceList().poll()) != null)
		{
			LOGGER.log(Level.FINE, "processing a command");
			f.execute(this);
			LOGGER.log(Level.FINE, "done processing a command");
		}

	}

	/**
	 * while the underlying implementation is still running it keeps processing all the received enforces.
	 */
	public synchronized void run()
	{
		Thread.currentThread().setName(getClass().getName()+" Thread");
		while (isRunning())
		{
			processAllCommand();

			try
			{
				this.wait(NetworkSettings.THREAD_CHECK_RATE);
			}
			catch (Exception e)
			{
				LOGGER.log(Level.SEVERE, "interrupted {0}", e.getMessage());
			}
		}
		connectionClosedCallback.call(this);
	}
}
