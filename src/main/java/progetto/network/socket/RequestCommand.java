package progetto.network.socket;

import progetto.network.IRoomRequest;

/**
 * allow to send a request from client to the server
 */
class RequestCommand implements INetworkCommand<SocketHandler> {
	private IRoomRequest request;

	RequestCommand(IRoomRequest req) {
		request = req;
	}

	public void execute(SocketHandler mng) {
		mng.getRequestCallback().call(request);
	}
}
