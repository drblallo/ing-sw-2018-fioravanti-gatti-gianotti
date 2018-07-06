package progetto.network;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class server connection is the class that uses the various implementation of INetworkHandler to perform the
 * room and server level behaviour.
 *
 * It requires an already built INetworkHandler to be created, since it cannot know how connection are established.
 * @author Massimo
 */
final class ServerConnection {

	private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

	private final INetworkHandler handler;
	private final Queue<IRoomRequest> reqQueue = new ConcurrentLinkedQueue<>();
	private int playerID = -1;

	/**
	 * Builds a new ServerConnection
	 * @param h the handler that will be used to exchange information with the client
	 */
	ServerConnection(INetworkHandler h)
	{
		handler = h;
		handler.getRequestCallback().addObserver(this::offerRequest);
		reqQueue.add(new EncapsulationRoomRequest(new FetchMyIDRequest()));
	}

	/**
	 * appends a request to the pending request queue
	 * @param req the request to be appended
	 */
	private void offerRequest(IRoomRequest req)
	{
		reqQueue.offer(req);
	}

	/**
	 *
	 * @return true if the underlying implementation is still running
	 */
	boolean isRunning() {
		return handler.isRunning();
	}

	/**
	 *
	 * @return the oldest pending request popping it.
	 */
	IRoomRequest popRequest()
	{
		return reqQueue.poll();
	}

	/**
	 *
	 * @return the oldest pending request without popping it.
	 */
	IRoomRequest peekRequest()
	{
		return reqQueue.peek();
	}

	/**
	 *
	 * @return the player if of this player. -1 if the player creation has not been processed by server.
	 */
	int getPlayerID() {
		return playerID;
	}

	/**
	 * Tries to send a message to the client.
	 * Can be called from any thread, since the underlying implementation must ensure that messages are thread safe.
	 *
	 * There is no requirements for this message to be received in order.
	 * @param message the message that must be sent.
	 */
	void sendMessage(String message) {
		handler.sendMessage(message);
	}

	/**
	 * sets the player id. Should be called only by the id manager, and only once.
	 * notifies the other end that id has changed.
	 * @param id the new id of the player.
	 */
	void setID(int id)
	{
		LOGGER.log(Level.FINE, "setting playerID {0}", id);
		playerID = id;
		sendID();
	}

	/**
	 * forces the client to change his player info.
	 */
	void sendID() {
		handler.sendEnforce(new SetPlayerInfoEnforce(playerID));
	}

	/**
	 * notifies the client with the new values of the room
	 * @param r the room view that must be sent.
	 */
	void onRoomModified(RoomView r)
	{
		handler.sendEnforce(new SetRoomInfoEnforce(r));
	}

	/**
	 * notifies that the player is no longer in the previous room, and that
	 * the sync object is therefore changed.
	 * @param ogg the new sync object
	 */
	void onRoomChanged(ISync ogg)
	{
		handler.sendEnforce(new SyncStateEnforce(ogg));
	}

	/**
	 * forces the player to check if the syncobject is still synced
	 * @param ogg the server side version of the object
	 */
	void checkSynch(ISync ogg)
	{
		handler.sendEnforce(new SyncCheckEnforce(ogg.getItemCount(), ogg.getHash()));
	}

	/**
	 * tries to tear down the connection gracefully
	 */
	void disconnect()
	{
		handler.disconnect(true);
	}

	/**
	 * forces the player to update his information about the server state.
	 * @param state the server state that must be sent.
	 */
	void sendServerState(ServerStateView state)
	{
		handler.sendEnforce(new ServerStateTransferEnforce(state));
	}

	/**
	 * forces the client to append a synch string to his sync object.
	 * @param s the string to be sent
	 */
	void sendSyncCommand(Serializable s)
	{
		handler.sendEnforce(new SyncStringEnforce(s));
	}

	void sendEnforce(IEnforce f)
	{
		handler.sendEnforce(f);
	}
}
