package progetto.network;

import java.io.Serializable;

/**
 * A player view is the collection of relevant information that regards a player.
 * It is immutable.
 */
public final class PlayerView implements Serializable{
	private final int id;
	private final String name;
	private final int chairID;
	private final boolean isReady;

	/**
	 * Builds a new player view with the provided information
	 */
	public PlayerView(int id, String name, int chairID, boolean isReady) {
		this.id = id;
		this.name = name;
		this.chairID = chairID;
		this.isReady = isReady;
	}

	/**
	 *
	 * @return the id of the player represented by this object.
	 */
	public int getId() {
		return id;
	}

	/**
	 *
	 * @return the of the player represented by this object.
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @return the chair id of the player represented by this object.
	 */
	public int getChairID() {
		return chairID;
	}

	/**
	 *
	 * @return true if the player represented by this object is ready, false otherwise.
	 */
	public boolean isReady() {
		return isReady;
	}
}
