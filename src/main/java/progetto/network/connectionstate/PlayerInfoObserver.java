package progetto.network.connectionstate;

import progetto.utils.IObserver;

final class PlayerInfoObserver implements IObserver<PlayerInfo> {
	private Room r;
	PlayerInfoObserver(Room room)
	{
		r = room;
	}


	public void notifyChange(PlayerInfo ogg) {
		r.getChangeEvent().call(r);
	}
}
