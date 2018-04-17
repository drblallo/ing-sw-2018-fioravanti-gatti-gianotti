package progetto.network;

public final class NetworkSettings {

	public static final int DEFAULT_TIME_TO_LIVE = 500;
	public static final int MAX_TIME_TO_LIVE_SKIPPED = 3;
	public static final int CHECK_SYNC_RATEO = 5;
	private static final int EASTER_EGG = 42;
	private int easterEgg = EASTER_EGG;

	public int getEasterEgg() {
		return easterEgg;
	}

}
