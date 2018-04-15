package progetto.network.socket.server;

import progetto.network.ConnectionsManager;
import progetto.network.INetworkClientHandler;
import progetto.network.ISync;
import progetto.network.NetworkServer;
import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.network.socket.AbstractSocketManager;
import progetto.network.socket.MessageCommand;
import progetto.network.socket.client.*;
import progetto.utils.Callback;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the INetworkClientHandler for the socket implementation
 */
public final class ClientHandler extends AbstractSocketManager implements INetworkClientHandler
{

	private static final Logger LOGGER = Logger.getLogger( ClientHandler.class.getName() );

	private final NetworkServer server;
	private int playerID = -1;
	private final RoomObserver roomObserver = new RoomObserver(this);
	private final PlayerChangedRoomObserver playerChangedRoomObserver = new PlayerChangedRoomObserver(this);
	private final Callback<INetworkClientHandler> connectionClosedCallbakc = new Callback<INetworkClientHandler>();

	public ClientHandler(Socket soc, NetworkServer s)
	{
		super(soc);
		server = s;
		s.getServerState().getPlayerChangedRoom().addObserver(playerChangedRoomObserver);

	}

	synchronized void replaceObservers()
	{
		Room r = getRoom();
		if (r != null) {
			r.getChangeEvent().addObserver(roomObserver);
			sendCommand(new ISyncStateTransferCommand(getSynchronizedObject()));
		}
	}

	synchronized ISync getSynchronizedObject()
	{
		return server.getConnectionsManager().getSyncObjOfRoom(getRoomID());
	}

	ServerState getServerState()
	{
		return server.getConnectionsManager().getServerState();
	}

	protected void onTearDown()
	{
		server.getServerState().getPlayerChangedRoom().removeObserver(playerChangedRoomObserver);
		connectionClosedCallbakc.call(this);

	}

	ConnectionsManager getManager()
	{
		return server.getConnectionsManager();
	}

	void sendRoomInfo()
	{
		sendCommand(new SetRoomInfoCommand(getRoom()));
	}

	private Room getRoom()
	{
		return getServerState().getRoom(getRoomID());
	}

	public int getPlayerID()
	{
		return playerID;
	}

	public int getRoomID() {
		Room r =getServerState().getRoomOfPlayer(getPlayerID());
		if (r == null)
			return -1;
		return r.getRoomID();
	}

	public void setID(int id) {
		playerID = id;
		sendCommand(new SetPlayerIDCommand(id));
	}

	public void sendMessage(String message)
	{
		LOGGER.log(Level.FINE, "Sending message {0}", message);
		sendCommand(new MessageCommand(message));
	}

	public Callback<INetworkClientHandler> getConnectionLostCallback() {
		return connectionClosedCallbakc;
	}

	public synchronized void sendSyncCommand(String syncCommand)
	{
		sendCommand(new SyncClientCommand(syncCommand));
	}

	public synchronized void checkSyncronization()
	{
		ISync ogg = getSynchronizedObject();
		sendCommand(new SyncCheckCommand(ogg.getStringCount(), ogg.getHash(ogg.getStringCount())));
	}

	public void sendServerState()
	{
		synchronized (server.getServerState()) {
			sendCommand(new ServerStateTransferCommand(server.getServerState()));
		}
	}


}
