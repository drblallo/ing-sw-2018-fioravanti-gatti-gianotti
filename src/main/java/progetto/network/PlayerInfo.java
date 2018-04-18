package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * A PlayerInfo is a object holding the in room info about a player.
 * It's up to the network to keep this information right. New players info must be build from a room,
 * so that a right id can be provided.
 *
 * Player name is unique inside a room, player id is unique inside a server.
 */
public final class PlayerInfo
{
	private static final Logger LOGGER = Logger.getLogger(PlayerInfo.class.getName());
	private String playerName = "No Name Selected";
	private int chairID = -1;
	private boolean isReady = false;
	private int playerID;

	private ServerConnection handler;

	PlayerInfo(String pln, int id, ServerConnection handler)
	{
		playerName = pln;
		playerID = id;
		this.handler = handler;
	}

	AbstractRoomRequest peekRequest()
	{
		return handler.peekRequest();
	}

	AbstractRoomRequest popRequest()
	{
		return handler.popRequest();
	}

	public ServerConnection getHandler() {
		return handler;
	}

	/***
	 *
	 * @return the current chair of a player. Default chair is -1 that stands for observers.
	 */
	public synchronized int getChairID() {
		return chairID;
	}

	/***
	 * Force set the chair of this player, cannot be used directly, this must be done throught the player room
	 * so that it can be checked if the room already has a player in that chair.
	 *
	 * Triggers chairChangedEvent event if the new chair is equal to the old one. The old value will be passed
	 * as argument.
	 * @param id
	 */
	synchronized void setChairID(int id) {
		LOGGER.log(Level.FINE, "Setting up new chair id {0}", id);
		chairID = id;
	}

	/**
	 * Set the new ready value of this player. Triggers readyStateChangedEvent even if the new value is equal to the
	 * old one. The old value will be passed as argument.
	 *
	 * @param ready new ready value.
	 */
	synchronized void setReady(boolean ready) {
		LOGGER.log(Level.FINE, "Setting up new ready value {0}", ready);
		isReady = ready;
	}

	/***
	 *
	 * @return the id of this player. Player ids are unique inside a server.
	 */
	public synchronized int getPlayerID() {
		return playerID;
	}


	public PlayerInfoView getView()
	{
		return new PlayerInfoView(playerID, playerName, chairID, isReady);
	}

}
