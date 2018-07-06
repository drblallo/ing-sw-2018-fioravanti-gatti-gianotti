package progetto.network;

import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * A Player is a object holding the in room info about a player.
 * It's up to the network to keep this information right. New players info must be build from a room,
 * so that a right id can be provided.
 *
 * Player name is unique inside a room, player id is unique inside a server.
 * @author Massimo
 */
public final class Player
{
	private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
	private final String playerName;
	private int chairID = -1;
	private boolean isReady = false;
	private PlayerView lastSnapshot = null;

	private ServerConnection handler;

	/**
	 * builds a playerInfo from a name and a server connection
	 * @param playerName the name with which this player will be known
	 * @param handler the sever connection that allow to send message to the client
	 */
	public Player(String playerName, ServerConnection handler)
	{
		this.playerName = playerName;
		this.handler = handler;
		updateSnapshot();
	}

	/**
	 *
	 * @return the oldest pending request made from the player, without popping in
	 */
	IRoomRequest peekRequest()
	{
		return handler.peekRequest();
	}

	/**
	 *
	 * @return the oldest pending request, and removes it from the queue
	 */
	IRoomRequest popRequest()
	{
		return handler.popRequest();
	}

	/**
	 *
	 * @return the server connection that is being currently used by this Player
	 */
	public ServerConnection getHandler()
	{
		return handler;
	}

	/***
	 *
	 * @return the current chair of a player. Default chair is -1 that stands for observers.
	 */
	public synchronized int getChairID()
	{
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
	synchronized void setChairID(int id)
	{
		LOGGER.log(Level.FINE, "Setting up new chair id {0}", id);
		chairID = id;
		updateSnapshot();
	}

	/**
	 * Set the new ready value of this player. Triggers readyStateChangedEvent even if the new value is equal to the
	 * old one. The old value will be passed as argument.
	 *
	 * @param ready new ready value.
	 */
	synchronized void setReady(boolean ready)
	{
		LOGGER.log(Level.FINE, "Setting up new ready value {0}", ready);
		isReady = ready;
		updateSnapshot();
	}

	/***
	 *
	 * @return the id of this player. Player ids are unique inside a server.
	 */
	public synchronized int getPlayerID()
	{
		return handler.getPlayerID();
	}

	/**
	 *
	 * @return the view representing the current state of this room
	 */
	public PlayerView getView()
	{
		return lastSnapshot;
	}

	/**
	 * call this function every time a modification of this object happens, this will update the snapshot state of
	 * this player.
	 *
	 * This will ensure that as long as the updates are made in thread safe environement the view can be recovered
	 * from any thread.
	 */
	private void updateSnapshot()
	{
		lastSnapshot = new PlayerView(handler.getPlayerID(), playerName, chairID, isReady);
	}

}
