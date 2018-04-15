package progetto.network;

import progetto.network.connectionstate.Room;

final class PickChairRequest extends AbstractRequest{
		private int charID;

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		Room r = manager.getServerState().getRoomOfPlayer(serverConnection.getPlayerID());
		if (r != null)
			r.setPlayerChair(serverConnection.getPlayerID(), charID);
	}

	PickChairRequest(int charID) {
		this.charID = charID;
	}
}
