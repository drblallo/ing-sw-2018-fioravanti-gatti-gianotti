package progetto.network.socket.server;

import progetto.network.socket.AbstractNetworkCommand;

public class FetchServerStateCommand extends AbstractNetworkCommand<ClientHandler>
{

	protected void execute(ClientHandler handler)
	{
		handler.sendServerState();
	}
}
