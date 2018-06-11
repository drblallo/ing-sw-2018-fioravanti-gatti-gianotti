package progetto.model;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton
 * ArrayList of Window Frame Couples. Upload by file JSON using GSON.
 */
public final class WindowFrameCoupleArray {

	private static WindowFrameCoupleArray instance = null;

	private WindowFrameCoupleArrayInternal windowFrameCoupleArrayInternal = new WindowFrameCoupleArrayInternal();

	private static final Logger LOGGER = Logger.getLogger(WindowFrameCoupleArray.class.getName());

	/**
	 * Constructor
	 */
	private WindowFrameCoupleArray()
	{
		String path = "windowFrameCouples.json";
		readWindowFrameCouples(path);
	}

	public static synchronized WindowFrameCoupleArray getInstance()
	{
		if(instance == null)
		{
			instance = new WindowFrameCoupleArray();
		}
		return instance;
	}

	/**
	 * Get list of window frame couples
	 * @return
	 */
	public List<WindowFrameCouple> getList()
	{
		return windowFrameCoupleArrayInternal.getWindowFrameCouples();
	}

	/**
	 * Read window frame couples by file
	 */
	public void readWindowFrameCouples(String path)
	{
		if(path.isEmpty())
		{
			LOGGER.log(Level.SEVERE, "Empty path");
			return;
		}

		try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), java.nio.charset.Charset.defaultCharset()))
		{
			Gson gson = new Gson();
			windowFrameCoupleArrayInternal = gson.fromJson(inputStreamReader, WindowFrameCoupleArrayInternal.class);
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage());
			LOGGER.log(Level.WARNING, "File reading has failed");
		}
	}

}
