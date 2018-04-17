package progetto.network.connectionstate;

import progetto.utils.Callback;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * A PlayerInfo is a object holding the in room info about a player.
 * It's up to the network to keep this information right. New players info must be build from a room,
 * so that a right id can be provided.
 *
 * Player name is unique inside a room, player id is unique inside a server.
 */
public final class PlayerInfo implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(PlayerInfo.class.getName());
	private String playerName = "No Name Selected";
	private int chairID = -1;
	private boolean isReady = false;
	private int playerID;

	private transient Callback<Integer> chairChangedEvent = new Callback<Integer>();
	private transient Callback<Boolean> readyStateChangedEvent = new Callback<Boolean>();
	private transient Callback<PlayerInfo> changedEvent = new Callback<PlayerInfo>();

	PlayerInfo(String pln, int id) {
		playerName = pln;
		playerID = id;
	}

	/***
	 *
	 * @return the name of this player. It cannot be changed once the player is created.
	 */
	public String getPlayerName() {
		return playerName;
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
		int oldChair = chairID;
		chairID = id;
		chairChangedEvent.call(oldChair);
		changedEvent.call(this);
	}

	/***
	 *
	 * @return true if the player status is ready, false otherwise. Default value is false.
	 */
	public synchronized boolean isReady() {
		return isReady;
	}

	/**
	 * Set the new ready value of this player. Triggers readyStateChangedEvent even if the new value is equal to the
	 * old one. The old value will be passed as argument.
	 *
	 * @param ready new ready value.
	 */
	public synchronized void setReady(boolean ready) {
		LOGGER.log(Level.FINE, "Setting up new ready value {0}", ready);
		boolean oldReady = isReady;
		isReady = ready;
		readyStateChangedEvent.call(oldReady);
		changedEvent.call(this);
	}

	/***
	 *
	 * @return the id of this player. Player ids are unique inside a server.
	 */
	public synchronized int getPlayerID() {
		return playerID;
	}

	/**
	 * @return true if the chair id of this player is -1
	 */
	public boolean isObserver() {
		return chairID == -1;
	}

	/**
	 * @return the callback that is called when the ready state of the player changes
	 */
	public Callback<Boolean> getReadyStateChangedEvent() {
		return readyStateChangedEvent;
	}

	/**
	 * @return the callback that is called when the chair of the player changes
	 */
	public Callback<Integer> getChairChangedEvent() {
		return chairChangedEvent;
	}

	/**
	 * @return the callback that is called when something inside this object changes
	 */
	public Callback<PlayerInfo> getChangedEvent() {
		return changedEvent;
	}

	/**
	 * Set the state of this object equals to the state of info. Triggers all the related callbacks
	 */
	synchronized void setInfo(PlayerInfo info) {
		LOGGER.fine("setting up player info");
		playerID = info.playerID;
		playerName = info.playerName;
		setReady(info.isReady);
		setChairID(info.getChairID());
	}

	/**
	 * @return a clone of this object, does not clone observer
	 */
	public synchronized PlayerInfo deepCopy() {
		LOGGER.fine("cloning object");
		PlayerInfo p = new PlayerInfo(playerName, playerID);
		p.chairID = chairID;
		p.isReady = isReady;
		return p;
	}
}
