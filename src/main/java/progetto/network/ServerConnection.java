package progetto.network;

import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServerConnection {

	private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

	private final INetworkClientHandler handler;
	private final Queue<AbstractRequest> reqQueue = new ConcurrentLinkedQueue<AbstractRequest>();
	private int playerID = -1;

	ServerConnection(INetworkClientHandler h) {
		handler = h;
		handler.getRequestCallback().addObserver(new IObserver<AbstractRequest>() {
			public void notifyChange(AbstractRequest ogg) {
				reqQueue.offer(ogg);
			}
		});

		reqQueue.add(new FetchMyIDRequest());
	}

	public boolean isRunning() {
		return handler.isRunning();
	}

	public AbstractRequest popRequest() {
		return reqQueue.poll();
	}

	public int getPlayerID() {
		return playerID;
	}

	public synchronized void sendMessage(String message) {
		handler.sendMessage(message);
	}

	public void setID(int id) {
		LOGGER.log(Level.INFO, "setting playerID {0}", id);
		playerID = id;
	}

	public void sendID() {
		handler.sendEnforce(new SetPlayerInfoEnforce(playerID));
	}

	public void onRoomModified(Room r) {
		if (r != null)
			r = r.deepCopy();

		handler.sendEnforce(new SetRoomInfoEnforce(r));
	}

	public void onRoomChanged(Room r, ISync ogg) {

		if (r != null) {
			handler.sendEnforce(new SetRoomInfoEnforce(r.deepCopy()));
			handler.sendEnforce(new SyncStateEnforce(ogg));
		} else
			handler.sendEnforce(new SetRoomInfoEnforce(null));
	}

	public void checkSynch(ISync ogg) {
		if (ogg != null)
			handler.sendEnforce(new SyncCheckEnforce(ogg.getStringCount(), ogg.getHash()));
	}

	public void disconnect() {
		handler.disconnect(true);
	}

	/**
	 * @return the callaback that is called every time a message is received
	 */
	public Callback<String> getMessageCallback() {
		return handler.getMessageCallback();
	}

	public void sendServerState(ServerState state) {
		handler.sendEnforce(new ServerStateTransferEnforce(state.deepCopy()));
	}

	public void sendSyncCommand(String s) {
		handler.sendEnforce(new SyncStringEnforce(s));
	}
}
