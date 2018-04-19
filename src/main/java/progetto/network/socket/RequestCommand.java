package progetto.network.socket;

import progetto.network.AbstractRoomRequest;

/**
 * allow to send a request from client to the server
 */
class RequestCommand extends AbstractNetworkCommand<ClientHandler> {
	private AbstractRoomRequest request;

	RequestCommand(AbstractRoomRequest req) {
		request = req;
	}

	void execute(ClientHandler mng) {
		mng.getRequestCallback().call(request);
	}
}
