package progetto.network.connectionstate;

import progetto.utils.IObserver;

/**
 * This observer is attached to every player that is inside a room.
 * It's triggered when changes somehow and forces the room to notify that herself is changed
 */
final class PlayerInfoObserver implements IObserver<PlayerInfo> {
	private Room r;
	PlayerInfoObserver(Room room)
	{
		r = room;
	}

	public void notifyChange(PlayerInfo ogg) {
		r.getChangeEvent().call(r); //notifies that a player is somehow changed inside a room
	}
}
