package progetto.game;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Couple of Window Frame (1 Window Pattern card -> 2 sides)
 */
public class WindowFrameCouple implements Serializable {

	private static final int NUMBER_OF_SIDES = 2;

	private WindowFrame[] windowFrame = new WindowFrame[NUMBER_OF_SIDES];

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

	public int getFavorToken(int side)
	{
		if(side==0 || side==1)
		{
			return windowFrame[side].getFavorToken();
		}
		return 0;
	}

	public Color getColorBond(int side, int x, int y)
	{
		if(side==0 || side==1)
		{
			return windowFrame[side].getColorBond(x, y);
		}
		return null;
	}

	public Value getValueBond(int side, int x, int y)
	{
		if(side==0 || side==1)
		{
			return windowFrame[side].getValueBond(x, y);
		}
		return null;
	}

	public String getName(int side)
	{
		if(side==0 || side==1)
		{
			return windowFrame[side].getName();
		}
		return null;
	}
}
