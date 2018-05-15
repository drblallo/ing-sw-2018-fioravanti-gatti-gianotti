package progetto.game;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Couple of Window Frame (1 Window Pattern card with 2 sides)
 */
public class WindowFrameCouple implements Serializable {

	private static final int NUMBER_OF_SIDES = 2;

	private final WindowFrame[] windowFrame = new WindowFrame[NUMBER_OF_SIDES];

	/**
	 * Constructor
	 * @param frameA JSONArray of the window frame of first side of the card
	 * @param frameB JSONArray of the window frame of second side of the card
	 */
	WindowFrameCouple(JSONArray frameA, JSONArray frameB)
	{
		windowFrame[0] = new WindowFrame(frameA);
		windowFrame[1] = new WindowFrame(frameB);
	}

	/**
	 * Get window frame
	 * @param side side of the windowFrameCouple to return
	 * @return the selected side of the card
	 */
	public WindowFrame getWindowFrame(int side)
	{
		if(side==0 || side==1)
		{
			return windowFrame[side];
		}
		return null;
	}

}
