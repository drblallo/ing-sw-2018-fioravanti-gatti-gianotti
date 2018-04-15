package progetto.network;

import progetto.network.connectionstate.Room;
import progetto.network.connectionstate.ServerState;
import progetto.utils.Callback;
import progetto.utils.IObserver;

final class ServerConnection {

	private final class ConnectionLostObserver implements IObserver<INetworkClientHandler>
	{
		private ServerConnection serverConnection;
		private ConnectionLostObserver(ServerConnection s)
		{
			serverConnection = s;
		}

		public void notifyChange(INetworkClientHandler ogg) {
			serverConnection.getConnectionLostCallback().call(serverConnection);
		}
	}

	private final INetworkClientHandler handler;
	private int playerID = -1;
	private final Callback<ServerConnection> connectionLostCallback = new Callback<ServerConnection>();

	ServerConnection(INetworkClientHandler h)
	{
		handler = h;
		handler.getConnectionLostCallback().addObserver(new ConnectionLostObserver(this));
	}

	public int getPlayerID()
	{
		return playerID;
	}

	public void sendMessage(String message)
	{
		handler.sendMessage(message);
	}

	public void setID(int id)
	{
		playerID = id;
		handler.sendEnforce(new SetPlayerInfoEnforce(playerID));
	}

	public void onRoomModified(Room r)
	{
		if (r != null)
			r = r.deepCopy();

		handler.sendEnforce(new SetRoomInfoEnforce(r));
	}

	public void onRoomChanged(Room r, ISync ogg)
	{

		if (r != null) {
			handler.sendEnforce(new SetRoomInfoEnforce(r.deepCopy()));
			handler.sendEnforce(new SyncStateEnforce(ogg));
		}
		else
			handler.sendEnforce(new SetRoomInfoEnforce(null));
	}

	public void checkSynch(ISync ogg)
	{
		if (ogg != null)
			handler.sendEnforce(new SyncCheckEnforce(ogg.getStringCount(), ogg.getHash()));
	}

	public void disconnect()
	{
		handler.disconnect(true);
	}

	/**
	 *
	 * @return the callaback that is called every time a message is received
	 */
	public Callback<String> getMessageCallback()
	{
		return handler.getMessageCallback();
	}

	/**
	 *
	 * @return the callback that is called every time a connection is lost
	 */
	public Callback<ServerConnection> getConnectionLostCallback()
	{
		return connectionLostCallback;
	}

	public Callback<AbstractRequest> getRequestCallback()
	{
		return handler.getRequestCallback();
	}

	public void sendServerState(ServerState state)
	{
		handler.sendEnforce(new ServerStateTransferEnforce(state.deepCopy()));
	}

	public void sendSyncCommand(String s) {
		handler.sendEnforce(new SyncStringEnforce(s));
	}
}
