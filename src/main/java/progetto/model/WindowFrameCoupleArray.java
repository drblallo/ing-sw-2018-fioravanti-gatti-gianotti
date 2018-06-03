package progetto.model;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ArrayList of Window Frame Couples. Upload by file JSON using GSON.
 */
public class WindowFrameCoupleArray {

	private static final Logger LOGGER = Logger.getLogger(Model.class.getName());
	private WindowFrameCoupleArrayInternal windowFrameCoupleArrayInternal = new WindowFrameCoupleArrayInternal();

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
		return windowFrameCoupleArrayInternal.getWindowFrameCouples();
	}

	/**
	 * Read window frame couples by file
	 */
	void readWindowFrameCouples(String path)
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
