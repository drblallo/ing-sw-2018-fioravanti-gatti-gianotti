package progetto.network;

import progetto.network.connectionstate.PlayerInfo;
import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.utils.IObserver;

import java.util.*;

/**
 * Connection manager is the class that keep track of every INetworkClientHandler
 * and of every synconject in every room
 */
public final class ConnectionsManager {

	private class RoomChangeNotifier implements IObserver<Integer>
	{
		private ConnectionsManager manager;

		public RoomChangeNotifier(ConnectionsManager manager) {
			this.manager = manager;
		}

		public void notifyChange(Integer ogg) {
			ServerConnection h = manager.getHandlerOfPlayer(ogg);
			Room r = serverState.getRoomOfPlayer(ogg);
			int roomID = -1;

			if (r != null)
				roomID = r.getRoomID();

			if (h != null)
				h.onRoomChanged(r, manager.getSyncObjOfRoom(roomID));

		}
	}


	private final ServerState serverState;
	private final ISyncFactory factory;
	private final HashMap<Integer, ISync> syncMan = new HashMap<Integer, ISync>();
	private final HashMap<Integer, ServerConnection> handlers = new HashMap<Integer, ServerConnection>();
	private final RoomChangedObserver roomChangedObserver = new RoomChangedObserver(this);

	ConnectionsManager(final ISyncFactory fact)
	{
		serverState = new ServerState();
		factory = fact;

		//every time a room is created
		serverState.getCreateRoomEvent().addObserver(new IObserver<Room>() {
			public void notifyChange(Room ogg) { 				//add a listener on that room
				syncMan.put(ogg.getRoomID(), factory.create());	//that add a new object related to that room
				ogg.getChangeEvent().addObserver(roomChangedObserver);
			}
		});

		serverState.getDeletedRoomEvent().addObserver(new IObserver<Room>() {
			public void notifyChange(Room ogg) {
				syncMan.remove(ogg.getRoomID());
				ogg.getChangeEvent().removeObserver(roomChangedObserver);
			}
		});

		serverState.getPlayerChangedRoom().addObserver(new RoomChangeNotifier(this));
	}

	/**
	 * @return the server state
	 */
	public ServerState getServerState() {
		return serverState;
	}

	/**
	 *
	 * @param hand a new handler that must be used
	 */
	synchronized void add(INetworkClientHandler hand)
	{
		ServerConnection con = new ServerConnection(hand);
		con.setID(serverState.getUnusedID());
		handlers.put(con.getPlayerID(), con);
		con.getRequestCallback().addObserver(new RequestObserver(con, this));
		new PlayerLeavedObserver(this, con);
	}

	/**
	 *
	 * @param hand handler that must be removed
	 */
	synchronized void remove(ServerConnection hand)
	{
		handlers.remove(hand.getPlayerID());
	}

	/**
	 * @param playerID the id
	 * @return the handler associated to a id
	 */
	public synchronized ServerConnection getHandlerOfPlayer(int playerID)
	{
		return handlers.get(playerID);
	}

	/**
	 * @param roomID id of the room you wish the room
	 * @return the sync object of a room
	 */
	public ISync getSyncObjOfRoom(int roomID)
	{
		return syncMan.get(roomID);
	}

	/**
	 * @return the cloned list of handlers
	 */
	public synchronized List<ServerConnection> getHandlers()
	{
		ArrayList<ServerConnection> ls = new ArrayList<ServerConnection>();
		ls.addAll(handlers.values());
		return ls;
	}

	/**
	 * process a sync command sent by a player to a room
	 */
	synchronized void processCommand(String syncString, int roomID, int callerID)
	{
		ISync sync = getSyncObjOfRoom(roomID);
		PlayerInfo info = serverState.getPlayer(callerID);
		Room r = serverState.getRoom(roomID);
		if (sync == null || info == null || r == null)
			return;

		if (!sync.isStringGood(syncString, info.getChairID()))
			return;

		sync.sendString(syncString);
		for (PlayerInfo p : r.getPlayers()) {
			ServerConnection h = handlers.get(p.getPlayerID());
			if (h != null)
				h.sendSyncCommand(syncString);
		}

		if (sync.getStringCount() % NetworkSettings.CHECK_SYNC_RATEO != 0)
			return;

		for (PlayerInfo p : r.getPlayers()) {
			ServerConnection h = handlers.get(p.getPlayerID());
			if (h != null)
				h.checkSynch(sync);
		}
	}
}
