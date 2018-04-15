package progetto.network.socket.server;

import progetto.network.INetworkClientHandler;
import progetto.network.INetworkModule;
import progetto.network.NetworkServer;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A socket server is a class that accept connections and redirect them on a new socket
 */
public final class SocketServer implements INetworkModule, Runnable{

	private static final Logger LOGGER = Logger.getLogger( SocketServer.class.getName() );
	private ServerSocket server = null;
	private NetworkServer netServer;
	private Callback<INetworkClientHandler> playerJoinedCallback = new Callback<INetworkClientHandler>();
	private Callback<INetworkClientHandler> playerLeavedCallback = new Callback<INetworkClientHandler>();
	private final int localPort;

	public SocketServer(int port, NetworkServer s) {
		localPort = port;
		netServer = s;


		netServer.addModules(this);
	}

	public synchronized void stop() {
		if (!isRunning())
		{
			LOGGER.log(Level.INFO, "Tried to stop a server that was not started or was already closed");
			return;
		}
		try
		{
			server.close();
		}
		catch (IOException e)
		{
			LOGGER.log(Level.WARNING, "Failed to close server socket: "  + e.getMessage());
		}
	}

	public void start() {
		if (server != null && !server.isClosed() && server.isBound())
		{
			LOGGER.log(Level.INFO, "Tried to start a server that is already open and bounded");
			return;
		}
		try
		{
			server = new ServerSocket(localPort);
		}
		catch (IOException e)
		{
			LOGGER.log(Level.WARNING, "Failed to start a server socket on "+ localPort+ " " + e.getMessage());
			return;
		}
		LOGGER.log(Level.INFO, "Opened a socket server");
		Thread serverThread;
		serverThread = new Thread(this);
		serverThread.start();
		LOGGER.info("Started a socket server");
	}

	public synchronized boolean isRunning()
	{
		return server != null && server.isBound() && !server.isClosed();
	}

	public Callback<INetworkClientHandler> getPlayerJoinedCallback() {
		return playerJoinedCallback;
	}

	public Callback<INetworkClientHandler> getPlayerLeavedCallback() {
		return playerLeavedCallback;
	}

	public void run() {
		LOGGER.log(Level.FINE, "Started a server");
		while (isRunning())
		{
			acceptConnection();
		}
	}

	private void acceptConnection()
	{
		try
		{
			Socket s = server.accept();
			ClientHandler cl = new ClientHandler(s, netServer);
			cl.getConnectionLostCallback().addObserver(new IObserver<INetworkClientHandler>() {
				public void notifyChange(INetworkClientHandler ogg) {
					playerLeavedCallback.call(ogg);
				}
			});
			playerJoinedCallback.call(cl);

		}
		catch (IOException e)
		{
			LOGGER.log(Level.SEVERE, "Failed to accept: "+ e.getMessage());
		}
	}

}
