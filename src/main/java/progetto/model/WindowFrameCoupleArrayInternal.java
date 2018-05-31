package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WindowFrameCoupleArrayInternal implements Serializable {

	private ArrayList<WindowFrameCouple> windowFrameCouples = new ArrayList<>();

	/**
	 * Get list of window frame couples
	 * @return
	 */
	public List<WindowFrameCouple> getWindowFrameCouples()
	{
		return new ArrayList<>(windowFrameCouples);
	}

}
