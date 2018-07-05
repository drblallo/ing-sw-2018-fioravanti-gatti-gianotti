package progetto.network;

public final class NetworkSettings {

	public static final int DEFAULT_TIME_TO_LIVE = 5000;
	public static final int MAX_TIME_TO_LIVE_SKIPPED = 10;
	public static final int CHECK_SYNC_RATEO = 5;
	private static final int EASTER_EGG = 42;
	private int easterEgg = EASTER_EGG;
	public static final int THREAD_CHECK_RATE = 10;
	public int getEasterEgg() {
		return easterEgg;
	}

}
