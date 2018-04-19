package progetto.network;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServerConnection {

	private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

	private final INetworkClientHandler handler;
	private final Queue<AbstractRoomRequest> reqQueue = new ConcurrentLinkedQueue<>();
	private int playerID = -1;

	ServerConnection(INetworkClientHandler h)
	{
		handler = h;
		handler.getRequestCallback().addObserver(this::offerRequest);
		reqQueue.add(new EncapsulationRoomRequest(new FetchMyIDRequest()));
	}

	public void offerRequest(AbstractRoomRequest req)
	{
		reqQueue.offer(req);
	}

	public boolean isRunning() {
		return handler.isRunning();
	}

	public AbstractRoomRequest popRequest()
	{
		return reqQueue.poll();
	}

	public AbstractRoomRequest peekRequest()
	{
		return reqQueue.peek();
	}

	public int getPlayerID() {
		return playerID;
	}

	public synchronized void sendMessage(String message) {
		handler.sendMessage(message);
	}

	public void setID(int id)
	{
		LOGGER.log(Level.FINE, "setting playerID {0}", id);
		playerID = id;
		sendID();
	}

	public void sendID() {
		handler.sendEnforce(new SetPlayerInfoEnforce(playerID));
	}

	public void onRoomModified(AbstractRoom r)
	{
		handler.sendEnforce(new SetRoomInfoEnforce(r.getView()));
	}

	public void onRoomChanged(ISync ogg)
	{
		handler.sendEnforce(new SyncStateEnforce(ogg));
	}

	public void checkSynch(ISync ogg)
	{
		handler.sendEnforce(new SyncCheckEnforce(ogg.getStringCount(), ogg.getHash()));
	}

	public void disconnect()
	{
		handler.disconnect(true);
	}

	public void sendServerState(ServerStateView state)
	{
		handler.sendEnforce(new ServerStateTransferEnforce(state));
	}

	public void sendSyncCommand(String s)
	{
		handler.sendEnforce(new SyncStringEnforce(s));
	}
}
