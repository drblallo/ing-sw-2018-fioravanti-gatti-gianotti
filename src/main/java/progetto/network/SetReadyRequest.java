package progetto.network;

import progetto.network.connectionstate.PlayerInfo;

final class SetReadyRequest extends AbstractRequest {

	private boolean isReady;

	SetReadyRequest(boolean isReady) {
		this.isReady = isReady;
	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		PlayerInfo info = manager.getServerState().getPlayer(serverConnection.getPlayerID());
		if (info != null)
			info.setReady(isReady);
	}
}
