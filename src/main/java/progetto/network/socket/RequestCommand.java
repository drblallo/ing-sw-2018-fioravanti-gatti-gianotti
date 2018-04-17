package progetto.network.socket;

import progetto.network.AbstractRequest;

/**
 * allow to send a request from client to the server
 */
class RequestCommand extends AbstractNetworkCommand<ClientHandler> {
	private AbstractRequest request;

	RequestCommand(AbstractRequest req) {
		request = req;
	}

	void execute(ClientHandler mng) {
		mng.getRequestCallback().call(request);
	}
}
