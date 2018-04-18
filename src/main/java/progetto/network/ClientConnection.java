package progetto.network;

import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ClientConnection implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());
	private RoomView roomInfo = new RoomView("No Name", -1);
	private final ISync synchronizedObj;
	private final Callback<ClientConnection> syncronizationFailedCallback = new Callback<ClientConnection>();
	private final Queue<AbstractEnforce> enforcesQueue = new ConcurrentLinkedQueue<AbstractEnforce>();
	private final INetworkClient handler;
	private ServerStateView serverState = new ServerStateView();
	private int playerID = -1;

	public ClientConnection(INetworkClient h, ISync ogg) {
		handler = h;
		synchronizedObj = ogg;
		handler.getEnforceCallback().addObserver(new IObserver<AbstractEnforce>() {
			public void notifyChange(AbstractEnforce ogg) {
				enforcesQueue.add(ogg);
			}
		});

		new Thread(this).start();
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
	public final synchronized Callback<ClientConnection> getSyncronizationFailedCallback() {
		return syncronizationFailedCallback;
	}

	/**
	 * ask the server for the current state of the server
	 */
	public synchronized void fetchServerState() {
		sendServerRequest(new FetchServerStateRequest());
	}

	public synchronized void fetchMyID(){
		sendServerRequest(new FetchMyIDRequest());
	}

	public synchronized int getPlayerID() {
		return playerID;
	}

	/**
	 * set the id of the current player
	 *
	 * @param plID new value of the player id
	 */
	final synchronized void setPlayerID(int plID) {
		LOGGER.log(Level.INFO, "Received a player ID from network: {0}", plID);
		playerID = plID;
	}

	/**
	 * Tries to join a game
	 *
	 * @param roomID     roomID you are trying to join
	 * @param playerName the name with which you will be called.
	 */
	public final synchronized void joinGame(int roomID, String playerName) {
		LOGGER.fine("Trying to join room");
		sendServerRequest(new JoinRoomRequest(roomID, playerName));
	}

	private final synchronized void sendServerRequest(AbstractServerRequest req)
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
	 * @param r
	 */
	final synchronized void setRoomInfo(RoomView r)
	{
		LOGGER.log(Level.INFO, "received new room info ");
		roomInfo = r;
	}

	/**
	 * sends a string to the syncObject.
	 *
	 * @param s
	 */
	final synchronized void processSyncCommand(String s) {
		getSynchronizedObject().sendString(s);
	}

	/**
	 * checks if the state of the object is correct
	 *
	 * @param state index of hash that must be compared
	 * @param hash  hash received by the server
	 */
	final synchronized void checkSync(int state, String hash) {
		ISync ogg = getSynchronizedObject();
		if (!ogg.getHash(state).equals(hash)) {
			LOGGER.log(Level.SEVERE, "SYNCHRONIZATION FAILED");
			syncronizationFailedCallback.call(this);
		}

	}

	public Callback<String> getMessageCallback() {
		return handler.getMessageCallback();
	}

	/**
	 * reload the entire state of the syncObject
	 *
	 * @param commands new state
	 */
	final synchronized void setFullState(List<String> commands) {
		LOGGER.fine("Receiving full state from server");
		getSynchronizedObject().clear();
		for (String s : commands)
			getSynchronizedObject().sendString(s);
	}

	/**
	 * @return the chair currently used by this chair, -1 if he is not in a room
	 */
	public synchronized int getChair() {

		PlayerInfoView info = roomInfo.getPlayer(playerID);
		if (info == null) {
			LOGGER.log(Level.INFO, "did not found player {0}", playerID);
			return -1;
		}
		LOGGER.log(Level.INFO, "found chair: {0}", info.getChairID());
		return info.getChairID();
	}

	/**
	 * @return the ready status of this player, false if he is not in a room
	 */
	public synchronized boolean isReady() {
		if (roomInfo.getPlayer(playerID) == null)
			return false;
		return roomInfo.getPlayer(playerID).isReady();
	}

	/**
	 * tries to set this player ready
	 *
	 * @param ready new state of the ready value
	 */
	public final synchronized void setReady(boolean ready) {
		handler.sendRequest(new SetReadyRoomRequest(ready));
	}

	public synchronized void sendSynString(String s) {
		handler.sendRequest(new SendSyncStringRoomRequest(s));
	}

	public synchronized void disconnect() {
		handler.disconnect(true);
	}

	public synchronized boolean isRunning() {
		return handler.isRunning();
	}

	public synchronized void processAllCommand()
	{
		AbstractEnforce f;
		while ((f = enforcesQueue.poll()) != null)
		{
			f.execute(this);
		}
	}

	public void run()
	{
		while (isRunning())
		{
			processAllCommand();
		}
	}
}