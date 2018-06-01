package progetto.integration.client;

import progetto.controller.IGameController;
import progetto.integration.AbstractGameSync;
import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.network.*;
import progetto.utils.Callback;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientGame extends AbstractGameSync implements IGameController
{
	private ClientConnection clientConnection;
	private static final Logger LOGGER = Logger.getLogger(ClientGame.class.getName());

	public ClientConnection getClientConnection() {
		return clientConnection;
	}

	public ClientGame(INetworkClient connection)
	{
		clientConnection = new ClientConnection(connection, this);
	}

	@Override
	public void sendAction(AbstractGameAction action) {
		LOGGER.log(Level.FINE, "sending action {0}", action.getName());
		clientConnection.sendSynString(action);
	}

	@Override
	public void processAllPendingAction() {
		//handled by the server
	}

	@Override
	public void processAction() {
		//handled by the server
	}

	@Override
	public IModel getModel() {
		return clientConnection.getProxy();
	}

	public Callback<String> getMessageCallback()
	{
		return clientConnection.getMessageCallback();
	}

	public Callback<RoomView> getRoomViewCallback()
	{
		return clientConnection.getRoomViewCallback();
	}

	public Callback<ServerStateView> getServerViewCallback()
	{
		return clientConnection.getServerStateViewCallback();
	}

	@Override
	public Callback<IEnforce> getEnforceCallback() {
		throw new UnsupportedOperationException("YOU ARE NOT A SERVER");
	}

	@Override
	public List<IEnforce> getNewPlayerEnforces() {
		throw new UnsupportedOperationException("YOU ARE NOT A SERVER");
	}
}
