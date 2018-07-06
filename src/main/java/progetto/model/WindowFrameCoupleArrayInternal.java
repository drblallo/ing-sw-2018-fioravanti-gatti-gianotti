package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Internal class of WindowFrameCoupleArray
 * Contains the list of WindowFrameCouple
 * @author Michele
 */
public class WindowFrameCoupleArrayInternal implements Serializable {

	private ArrayList<WindowFrameCouple> windowFrameCouples = new ArrayList<>();

	/**
	 * Get list of window frame couples
	 * @return list of window frame couples
	 */
	public List<WindowFrameCouple> getWindowFrameCouples()
	{
		return new ArrayList<>(windowFrameCouples);
	}

}
