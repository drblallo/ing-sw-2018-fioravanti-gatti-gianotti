package progetto.network;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The extension of Abstract room that is able to keep the sync object synchronized.
 */
public final class Room extends AbstractRoom
{

	private static final Logger LOGGER = Logger.getLogger(Room.class.getName());

	Room(String name, int id, ISync ogg)
	{
		super(name, id, ogg);
	}

	/**
	 * Tries to set the player to the new ready value. Does nothing if the player does not exist.
	 * @param playerID the player id that is trying to be set ready
	 * @param ready the new ready value
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
	 * @param playerID the id of the player
	 * @param newChairID the new chair value of the player
	 */
	public void setPlayerChair(int playerID, int newChairID)
	{
		Player info = getPlayerFromID(playerID);

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
	final void processCommand(Serializable syncString, int callerID)
	{
		LOGGER.log(Level.FINE, "received serializable");
		Player info = players.get(callerID);

		if (!getSyncOgg().isStringGood(syncString, info.getChairID()))
			return;

		getSyncOgg().sendString(syncString);

		for (Player p : players.values())
			p.getHandler().sendSyncCommand(syncString);

		if (getSyncOgg().getStringCount() % NetworkSettings.CHECK_SYNC_RATEO != 0)
			return;

		for (Player p : players.values())
			p.getHandler().checkSynch(getSyncOgg());
	}


}
