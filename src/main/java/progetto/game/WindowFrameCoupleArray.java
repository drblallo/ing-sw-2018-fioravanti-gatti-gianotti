package progetto.game;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ArrayList of Window Frame Couples. Upload by file JSON using GSON.
 */
public class WindowFrameCoupleArray implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
	private ArrayList<WindowFrameCouple> windowFrameCouples = new ArrayList<>();

	private static final WindowFrameCoupleArray array = new WindowFrameCoupleArray();

	public static List<WindowFrameCouple> getList()
	{
		return array.getWindowFrameCouples();
	}

	/**
	 * Constructor
	 */
	public WindowFrameCoupleArray()
	{
		String path = "windowFrameCouples.json";
		readWindowFrameCouples(path);
	}

	/**
	 * Constructor
	 * @param path file path
	 */
	WindowFrameCoupleArray(String path)
	{
		if(path.isEmpty())
		{
			LOGGER.log(Level.SEVERE, "Wrong path");
			windowFrameCouples = new ArrayList<>();
		}
		else
		{
			readWindowFrameCouples(path);
		}
	}

	/**
	 * Get list of window frame couples
	 * @return
	 */
	public List<WindowFrameCouple> getWindowFrameCouples()
	{
		return new ArrayList<>(windowFrameCouples);
	}

	/**
	 * Read window frame couples by file
	 */
	void readWindowFrameCouples(String path)
	{
		if(path.isEmpty())
		{
			LOGGER.log(Level.SEVERE, "Empty path");
			windowFrameCouples = new ArrayList<>();
			return;
		}

		try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), java.nio.charset.Charset.defaultCharset()))
		{
			WindowFrameCoupleArray windowFrameCoupleArray;
			Gson gson = new Gson();
			windowFrameCoupleArray = gson.fromJson(inputStreamReader, WindowFrameCoupleArray.class);
			windowFrameCouples = new ArrayList<>(windowFrameCoupleArray.getWindowFrameCouples());
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage());
			LOGGER.log(Level.WARNING, "File reading has failed");
			windowFrameCouples = new ArrayList<>();
		}
	}

}
