package progetto;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings {

	private static Settings settings;

	public int getSocketPort() {
		return socketPort;
	}

	public int getRmiPort() {
		return rmiPort;
	}

	public int getGameStartTimeOut() {
		return gameStartTimeOut;
	}

	public int getPlayerTurnTImeOut() {
		return playerTurnTImeOut;
	}

	private int socketPort;
	private int rmiPort;
	private int gameStartTimeOut;
	private int playerTurnTImeOut;

	public int getAllertTime() {
		return allertTime;
	}

	private int allertTime;
	private static final Logger LOGGER = Logger.getLogger(Settings.class.getName());

	public static synchronized Settings getSettings() {

		if (settings == null)
			load("settings.json");
		return settings;
	}

	private static synchronized void load(String path)
	{
		if(path.isEmpty())
		{
			LOGGER.log(Level.SEVERE, "Empty path");
		}

		try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), java.nio.charset.Charset.defaultCharset()))
		{
			Gson gson = new Gson();
			settings = gson.fromJson(inputStreamReader, Settings.class);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage());
			LOGGER.log(Level.WARNING, "File reading has failed");
			System.exit(-1);
		}

	}
}
