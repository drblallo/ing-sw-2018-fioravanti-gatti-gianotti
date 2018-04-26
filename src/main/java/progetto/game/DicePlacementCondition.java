package progetto.game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class where compliance with the positioning constraints of a dice in the window frame is verified
 *
 * Rules for placing dice:
 *  -First dice to be placed along the edges
 *  -Other dice near one of those already positioned (orthogonally or diagonally)
 *  -Respect the imposed value and color constraints of the scheme
 *  -No dice near orthogonally with same color and/or same value
 *  -No two dice in the same position
 *
 * Rules that can be ignored thanks to the effects of the cards:
 *  -Ingore color bond
 *  -Ingore value bond
 *  -Ignore constraint of proximity to another dice
*/
public class DicePlacementCondition {

	private static final int Y_MAX = 3;
	private static final int X_MAX = 4;

	private static final Logger LOGGER = Logger.getLogger(DicePlacementCondition.class.getName());

	private boolean ignoreColor;
	private boolean ignoreValue;
	private boolean ignoreAdjacent;

	private Dice dice;

	DicePlacementCondition(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		this.dice = dice;
		this.ignoreColor = ignoreColor;
		this.ignoreValue = ignoreValue;
		this.ignoreAdjacent = ignoreAdjacent;
	}

	public boolean canBePlaced(int x, int y, WindowFrame windowFrame, DicePlacedFrame dicePlacedFrame)
	{
		Logger.getLogger(DicePlacementCondition.class.getName()).getParent().getHandlers()[0].setLevel(Level.ALL);
		Logger.getLogger(DicePlacementCondition.class.getName()).setLevel(Level.ALL);

		boolean ok = true;

		if(!verifyPositions(x, y))          //Verify if x and y are valid positions
		{
			LOGGER.log(Level.FINE, "Wrong x and/or y");
			ok = false;
		}

		else if(dicePlacedFrame.getDice(x, y)!=null)    //Verify if the position x, y is free
		{
			LOGGER.log(Level.FINE, "Only one dice in a position");
			ok = false;
		}

		else if(!verifyColor(x, y, windowFrame))        //Verify color bond
		{
			LOGGER.log(Level.FINE, "Color bond not respected");
			ok = false;
		}

		else if(!verifyFirstDiceEdge(x, y, dicePlacedFrame))      //Verify first dice is positioned near the edge
		{
			LOGGER.log(Level.FINE, "The first dice must be positioned near the edge");
			ok = false;
		}

		else if(!verifyValue(x, y, windowFrame))        //Verify value bond
		{
			LOGGER.log(Level.FINE, "Value bond not respected");
			ok = false;
		}

		else if(dicePlacedFrame.getNDices()!=0 && !checkAdjacent(x, y, dicePlacedFrame))                //Verify the dice is positioned near an other dice (if it is not the first dice)
		{
			LOGGER.log(Level.FINE, "The dice must be positioned near an other dice");
			ok = false;
		}

		else if(!checkNearValue(x, y, dicePlacedFrame))                        //Verify the dice is not positioned near a dice with the same value or color
		{
			LOGGER.log(Level.FINE, "The dice can't be positioned near a dice with the same value");
			ok = false;
		}

		else if(!checkNearColor(x, y, dicePlacedFrame))                        //Verify the dice is not positioned near a dice with the same value or color
		{
			LOGGER.log(Level.FINE, "The dice can't be positioned near a dice with the same color");
			ok = false;
		}

		else
		{
			LOGGER.log(Level.FINE, "The dice can be positioned in the selected position");
		}

		return ok;
	}

	private boolean verifyPositions(int x, int y)
	{
		return(!(x<0 || x>X_MAX || y<0 || y>Y_MAX));
	}

	private boolean verifyColor(int x, int y, WindowFrame windowFrame)
	{
		if(ignoreColor)
		{
			return true;
		}
		return(!(windowFrame.getColorBond(x, y)!=null && windowFrame.getColorBond(x, y)!=dice.getColor()));
	}

	private boolean verifyValue(int x, int y, WindowFrame windowFrame)
	{
		if(ignoreValue)
		{
			return true;
		}
		return(!(windowFrame.getValueBond(x, y)!=null && windowFrame.getValueBond(x, y)!=dice.getValue()));
	}

	private boolean verifyFirstDiceEdge(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(dicePlacedFrame.getNDices()==0)
		{
			return ((x==0 || x==X_MAX) && (y==0 || y==Y_MAX));
		}
		return true;
	}

	private boolean checkAdjacent(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(ignoreAdjacent)
		{
			return true;
		}

		boolean found = false;
		ArrayList<Couple> couple = new ArrayList<>();
		couple.add(new Couple (-1, 0));
		couple.add(new Couple (-1, -1));
		couple.add(new Couple (-1, 1));
		couple.add(new Couple (0, -1));
		couple.add(new Couple (0, 1));
		couple.add(new Couple (1, -1));
		couple.add(new Couple (1, 0));
		couple.add(new Couple (1, 1));

		for (Couple c : couple)
		{
			if(verifyNear(x + c.getDx(), y + c.getDy(), dicePlacedFrame))
			{
				found = true;
			}
		}

		return found;
	}

	private boolean checkNearValue(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(ignoreValue)
		{
			return true;
		}

		boolean found = true;

		ArrayList<Couple> couple = new ArrayList<>();
		couple.add(new Couple (-1, 0));
		couple.add(new Couple (0, -1));
		couple.add(new Couple (0, 1));
		couple.add(new Couple (1, 0));

		for (Couple c : couple)
		{
			if(!verifyNearValue(x + c.getDx(), y + c.getDy(), dicePlacedFrame))
			{
				found = false;
			}
		}

		return found;
	}

	private boolean checkNearColor(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(ignoreColor)
		{
			return true;
		}

		boolean found = true;

		ArrayList<Couple> couple = new ArrayList<>();
		couple.add(new Couple (-1, 0));
		couple.add(new Couple (0, -1));
		couple.add(new Couple (0, 1));
		couple.add(new Couple (1, 0));

		for (Couple c : couple)
		{
			if(!verifyNearColor(x + c.getDx(), y + c.getDy(), dicePlacedFrame))
			{
				found = false;
			}
		}

		return found;
	}

	private boolean verifyNear(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(x<0 || y<0 || x>X_MAX || x>Y_MAX)
		{
			return false;
		}
		return(dicePlacedFrame.getDice(x, y)!=null);
	}

	private boolean verifyNearValue(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(x<0 || y<0 || x>X_MAX || x>Y_MAX)
		{
			return true;
		}
		return(!(dicePlacedFrame.getDice(x, y)!=null && (dice.getValue()==dicePlacedFrame.getDice(x, y).getValue())));
	}

	private boolean verifyNearColor(int x, int y, DicePlacedFrame dicePlacedFrame)
	{
		if(x<0 || y<0 || x>X_MAX || x>Y_MAX)
		{
			return true;
		}
		return (!(dicePlacedFrame.getDice(x, y)!=null && (dice.getColor()==dicePlacedFrame.getDice(x, y).getColor())));
	}

}
