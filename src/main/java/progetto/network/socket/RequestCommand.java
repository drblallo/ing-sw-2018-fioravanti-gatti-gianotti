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

	/**
	 * send a request to the server
	 * @param mng the manager that will receive this command
	 */
	public void execute(SocketHandler mng) {
		mng.getRequestCallback().call(request);
	}
}
