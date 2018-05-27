package progetto.clientintegration;

import progetto.game.*;
import progetto.gui.GamePaneController;
import progetto.network.ClientConnection;
import progetto.network.INetworkClient;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientGame extends AbstractGameSync implements IExecuibleGame
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
	public AbstractProcessor<AbstractGameAction> getActionQueue() {
		return getGame().getActionQueue();
	}

	@Override
	public RoundTrack getRoundTrack() {
		return getGame().getRoundTrack();
	}

	@Override
	public PlayerBoard getPlayerBoard(int index) {
		return getGame().getPlayerBoard(index);
	}

	@Override
	public MainBoard getMainBoard() {
		return getGame().getMainBoard();
	}

	@Override
	public DiceBag getDiceBag() {
		return getGame().getDiceBag();
	}

}
