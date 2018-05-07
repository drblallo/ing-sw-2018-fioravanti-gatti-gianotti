package progetto.game;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Couple of Window Frame (1 Window Pattern card -> 2 sides)
 */
public class WindowFrameCouple implements Serializable {

	private static final int NUMBER_OF_SIDES = 2;

	private final WindowFrame[] windowFrame = new WindowFrame[NUMBER_OF_SIDES];

	WindowFrameCouple(JSONArray frameA, JSONArray frameB)
	{
		windowFrame[0] = new WindowFrame(frameA);
		windowFrame[1] = new WindowFrame(frameB);
	}

	public WindowFrame getWindowFrame(int side)
	{
		if(side==0 || side==1)
		{
			return windowFrame[side];
		}
		return null;
	}

}
