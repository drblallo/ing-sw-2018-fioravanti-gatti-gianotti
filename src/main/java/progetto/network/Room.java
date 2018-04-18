package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Room contains all the information needed to keep track of the players inside a room.
 * Inside a room players name are unique.
 */
public final class Room extends AbstractRoom
{

	private static final Logger LOGGER = Logger.getLogger(Room.class.getName());

	Room(String name, int id, ISync ogg)
	{
		super(name, id, ogg);
	}

	/**
	 * @return the ID of this room, this value is unique inside a server.
	 */

	void setPlayerReady(int playerID, boolean ready)
	{
		if (players.get(playerID) != null)
			players.get(playerID).setReady(ready);

		notifyChange();
	}

	/**
	 * Sets the chair of a particular player, if that chair is already taken then nothing is done.
	 *
	 * @param playerID
	 * @param newChairID
	 */
	public void setPlayerChair(int playerID, int newChairID)
	{
		PlayerInfo info = getInfoFromID(playerID);

		if (info != null && (getPlayerOfChair(newChairID) == null || newChairID == -1))
		{
			info.setChairID(newChairID);
			LOGGER.log(Level.INFO, "Player took {0} chair", newChairID);
			notifyChange();
		}
	}



	/**
	 * process a sync command sent by a player to a room
	 */
	final void processCommand(String syncString, int callerID)
	{
		PlayerInfo info = players.get(callerID);

		if (getSyncOgg() == null || info == null)
			return;

		if (!getSyncOgg().isStringGood(syncString, info.getChairID()))
			return;

		getSyncOgg().sendString(syncString);

		for (PlayerInfo p : players.values())
			p.getHandler().sendSyncCommand(syncString);

		if (getSyncOgg().getStringCount() % NetworkSettings.CHECK_SYNC_RATEO != 0)
			return;

		for (PlayerInfo p : players.values())
			p.getHandler().checkSynch(getSyncOgg());
	}


}
