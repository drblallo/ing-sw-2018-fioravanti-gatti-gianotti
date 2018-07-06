package progetto.network.socket;

import progetto.network.IEnforce;
import progetto.network.INetworkClient;
import progetto.network.IRoomRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A socket client is a implementation of INetwork client, it is used on the client side to communicate with the server
 */
public final class SocketClient extends AbstractSocket implements INetworkClient {

	private final ConcurrentLinkedQueue<IEnforce> listOfEnforces = new ConcurrentLinkedQueue<>();

	public SocketClient(String ip, int port) {
		super(ip, port);
	}

	protected void addEnforce(IEnforce e)
	{
		listOfEnforces.offer(e);
	}

	@Override
	public ConcurrentLinkedQueue<IEnforce> getEnforceList() {
		return listOfEnforces;
	}

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
