package progetto.network;

final class SyncCheckEnforce implements IEnforce {

	private int state;
	private String h;

	SyncCheckEnforce(int stateIndex, String hash) {
		state = stateIndex;
		h = hash;
	}

	public void execute(ClientConnection mng) {
		mng.checkSync(state, h);
	}
}
