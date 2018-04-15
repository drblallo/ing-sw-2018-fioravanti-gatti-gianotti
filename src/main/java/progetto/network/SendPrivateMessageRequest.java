package progetto.network;

final class SendPrivateMessageRequest extends AbstractRequest {
	private String message;
	private int targetID;

	SendPrivateMessageRequest(String message, int targetID) {
		this.message = message;
		this.targetID = targetID;
	}

	public void execute(ConnectionsManager manager, ServerConnection serverConnection) {
		ServerConnection hand = manager.getHandlerOfPlayer(targetID);
		if (hand != null) {
			hand.sendMessage(message);
		}
	}
}
