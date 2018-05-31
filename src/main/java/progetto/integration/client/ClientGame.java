package progetto.integration.client;

import progetto.controller.IGameController;
import progetto.integration.AbstractGameSync;
import progetto.model.*;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;
import progetto.network.INetworkClient;
import progetto.utils.Callback;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientGame extends AbstractGameSync implements IGameController, IModel
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
		return this;
	}

	@Override
	public DataContainer<CommandQueueData> getCommandQueue() {
		return clientConnection.getProxy().getCommandQueue();
	}

	@Override
	public DataContainer<RoundTrackData> getRoundTrack() {
		return clientConnection.getProxy().getRoundTrack();
	}

	@Override
	public AbstractPlayerBoard getPlayerBoard(int index) {
		return clientConnection.getProxy().getPlayerBoard(index);
	}

	@Override
	public AbstractMainBoard getMainBoard() {
		return clientConnection.getProxy().getMainBoard();
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
