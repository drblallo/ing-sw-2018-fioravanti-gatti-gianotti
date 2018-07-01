package progetto.network.socket;

import progetto.network.NetworkSettings;
import progetto.utils.Callback;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the standard form in which the socket are allowed to exchange info.
 * T is the on connection lost callback type
 */
abstract class AbstractSocket implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(AbstractSocket.class.getName());
	private final Timer keepAliveTimer = new Timer();
	private final Callback<String> messageCallback = new Callback<>();
	private final Callback<AbstractSocket> connectionLostCallback = new Callback<>();
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private int ttl;


	/**
	 * Builds a socketManager from ip and port
	 */
	AbstractSocket(String target, int port) {
		LOGGER.log(Level.INFO, "Trying to log in on {0}", target);

		try {
			socket = new Socket(target, port);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Failed to connect to " + port + " on port " + target + " " + e.getMessage());
			return;
		}

		setUp();
	}

	/**
	 * Builds a socketManager around a already existing socket
	 *
	 * @param sock the socket that will comply to this behaviour
	 */
	AbstractSocket(Socket sock) {
		socket = sock;
		setUp();
	}

	/**
	 * @return the callback that is called when the timer signals that we are out of max time.
	 */
	public final Callback<AbstractSocket> getConnectionClosedCallback() {
		return connectionLostCallback;
	}

	/**
	 * @return the callback that is called every time a text message is received.
	 */
	public final Callback<String> getMessageCallback() {
		return messageCallback;
	}

	/**
	 * @return true if we recently received a keep alive message
	 */
	private boolean isAlive() {
		return ttl >= 0;
	}

	/**
	 * initializes the support members and stars the listening thread
	 */
	private void setUp() {
		ttl = NetworkSettings.MAX_TIME_TO_LIVE_SKIPPED;

		try
		{
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			new Thread(this).start();
		}
		catch (IOException e) {
			LOGGER.log(Level.WARNING, "Failed to create streams: " + e.getMessage());
		}

		keepAliveTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				tick();
			}
		}, 		NetworkSettings.DEFAULT_TIME_TO_LIVE,
				NetworkSettings.DEFAULT_TIME_TO_LIVE);

	}

	/**
	 * called when the connection is closed or the keep alive timer reaches zero
	 */
	private synchronized void tearDown()
	{
		LOGGER.info("tearing down a connection");
		keepAliveTimer.cancel();
		keepAliveTimer.purge();
		onTearDown();
		socket = null;
		connectionLostCallback.call(this);
	}

	protected abstract void onTearDown();

	/**
	 * set the current time to live
	 *
	 * @param timeToLive how many ttl tick can be skipped before shutting down the connection
	 */
	void setTTL(int timeToLive) {
		ttl = timeToLive;
	}


	/**
	 * called by the clock every KEEP_LIVE milliseconds
	 */
	private synchronized void tick() {
		LOGGER.log(Level.FINER,"ttl left {0}", ttl);
		KeepAliveCommand msg = new KeepAliveCommand();
		sendCommand(msg);

		ttl -= 1;
		if (ttl <= 0) disconnect(true);
	}

	/**
	 * Sends a command to the other side of the network
	 *
	 * @param msg the abstract message that must be sent
	 */
	final synchronized void sendCommand(INetworkCommand msg) {
		if (socket == null) {
			LOGGER.log(Level.SEVERE, "Trying to send command to null network");
			return;
		}

		try {
			LOGGER.log(Level.FINE, "Sending command {0}", msg.getClass().getName());
			out.writeObject(msg);
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Failed to send command trough network: " + e.getMessage());
		}
	}

	/**
	 * @return true if the socket exists, is not disconnected, is not closed and is alive
	 */
	public final boolean isRunning() {
		return isAlive() && socket != null && socket.isConnected() && !socket.isClosed();
	}

	/**
	 * disconnects from the network
	 *
	 * @param signalGoodBye true if you with to close the connection properly
	 */
	public final synchronized void disconnect(boolean signalGoodBye) {
		if (!isRunning()) {
			LOGGER.log(Level.FINE, "Tried to disconnect a closed socket.");
			return;
		}

		try {
			if (signalGoodBye)
			{
				sendCommand(new GoodByeCommand());
				socket.close();
			}
			tearDown();
		} catch (IOException e) { LOGGER.log(Level.WARNING, "Failed to disconnect " + e.getMessage());}
	}

	/**
	 * process the commands, this is called by the listening thread
	 */
	private void readCommands() {
		LOGGER.log(Level.FINER,"Reading commands");
		try {

			INetworkCommand cmd = (INetworkCommand) in.readObject();
			if (cmd != null) {
				LOGGER.log(Level.FINE, "Received command from network, {0}", cmd.getClass().getName());
				cmd.execute(this);
			}
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Failed to read object from socket: " + e.getMessage());
			tearDown();
		} catch (ClassNotFoundException e) { LOGGER.log(Level.SEVERE, "CLASS IS MISSING " + e.getMessage()); }
	}

	public void run() {
		Thread.currentThread().setName("Socket reading thread");
		while (isRunning()) {
			readCommands();
		}
		LOGGER.log(Level.INFO,"reached natural end");
	}

}
