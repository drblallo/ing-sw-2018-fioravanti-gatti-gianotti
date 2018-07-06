package progetto.network.socket;

import progetto.network.IEnforce;
import progetto.network.INetworkClient;
import progetto.network.IRoomRequest;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A socket client is a implementation of INetwork client, it is used on the client side to communicate with the server
 */
public final class SocketClient extends AbstractSocket implements INetworkClient {

	private final ConcurrentLinkedQueue<IEnforce> listOfEnforces = new ConcurrentLinkedQueue<>();

	/**
	 * creates a socket connection
	 * @param ip the target ip
	 * @param port the target port
	 */
	public SocketClient(String ip, int port) {
		super(ip, port);
	}

	/**
	 * add a enforce to the list of pending
	 * @param e the enforce to be addedd
	 */
	protected void addEnforce(IEnforce e)
	{
		listOfEnforces.offer(e);
	}

	@Override
	public Queue<IEnforce> getEnforceList() {
		return listOfEnforces;
	}

	/**
	 * send a request to the other side
	 * @param request request to be sent, implementation ensures that it will be sent
	 */
	public void sendRequest(IRoomRequest request) {
		sendCommand(new RequestCommand(request));
	}

	/**
	 * called when the connection gets closed.
	 */
	protected final void onTearDown() {
		//noting to do on tear down
	}


}
