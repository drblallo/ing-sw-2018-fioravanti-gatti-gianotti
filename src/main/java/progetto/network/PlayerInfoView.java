package progetto.network;

import java.io.Serializable;

public final class PlayerInfoView implements Serializable{
	private final int id;
	private final String name;
	private final int chairID;
	private final boolean isReady;

	public PlayerInfoView(int id, String name, int chairID, boolean isReady) {
		this.id = id;
		this.name = name;
		this.chairID = chairID;
		this.isReady = isReady;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getChairID() {
		return chairID;
	}

	public boolean isReady() {
		return isReady;
	}
}
