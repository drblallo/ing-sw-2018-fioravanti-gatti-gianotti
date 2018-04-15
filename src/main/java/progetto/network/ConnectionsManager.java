package progetto.network;

import progetto.network.connectionstate.PlayerInfo;
import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.utils.IObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class ConnectionsManager {

	private ServerState serverState;
	private ISyncFactory factory;
	private HashMap<Integer, ISync> syncMan = new HashMap<Integer, ISync>();
	private List<INetworkClientHandler> handlers = Collections.synchronizedList(new ArrayList<INetworkClientHandler>());

	public ConnectionsManager(final ISyncFactory fact)
	{
		serverState = new ServerState();
		factory = fact;
		serverState.getCreateRoomEvent().addObserver(new IObserver<Room>() {
			public void notifyChange(Room ogg) {
				syncMan.put(ogg.getRoomID(), factory.create());
			}
		});
	}

	public ServerState getServerState() {
		return serverState;
	}

	public synchronized void add(INetworkClientHandler hand)
	{
		handlers.add(hand);
	}

	public synchronized void remove(INetworkClientHandler hand)
	{
		handlers.remove(hand);
	}

	public synchronized INetworkClientHandler getHandlerOfPlayer(int playerID)
	{
		for (int a = 0; a < handlers.size(); a++)
			if (handlers.get(a).getPlayerID() == playerID)
				return handlers.get(a);
		return null;
	}

	public ISync getSyncObjOfRoom(int roomID)
	{
		return syncMan.get(roomID);
	}

	public synchronized List<INetworkClientHandler> getHandlers()
	{
		ArrayList<INetworkClientHandler> ls = new ArrayList<INetworkClientHandler>();
		ls.addAll(handlers);
		return ls;
	}

	public synchronized void processCommand(String syncString, int roomID, int callerID)
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
			INetworkClientHandler h = getHandlerOfPlayer(p.getPlayerID());
			if (h != null)
				h.sendSyncCommand(syncString);
		}

		if (sync.getStringCount() % NetworkSettings.CHECK_SYNC_RATEO != 0)
			return;

		for (PlayerInfo p : r.getPlayers()) {
			INetworkClientHandler h = getHandlerOfPlayer(p.getPlayerID());
			if (h != null)
				h.checkSyncronization();
		}
	}
}
