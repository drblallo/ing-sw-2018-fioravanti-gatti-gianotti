package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Immutable class where compliance with the positioning constraints of a dice in the window frame is verified
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
public final class DicePlacementCondition implements Serializable{

	private static final int Y_MAX = 3;
	private static final int X_MAX = 4;

	private static final Logger LOGGER = Logger.getLogger(DicePlacementCondition.class.getName());

	private final boolean ignoreColor;
	private final boolean ignoreValue;
	private final boolean ignoreAdjacent;

	private final Dice dice;

	/**
	 * Constructor to add a dice with ignore conditions
	 * @param dice to add
	 * @param ignoreColor boolean to set ignore Value constraints
	 * @param ignoreValue boolean to set ignore Color constraints
	 * @param ignoreAdjacent boolean to set ignore Adjacent constraints
	 */
	DicePlacementCondition(Dice dice, boolean ignoreColor, boolean ignoreValue, boolean ignoreAdjacent)
	{
		this.dice = dice;
		this.ignoreColor = ignoreColor;
		this.ignoreValue = ignoreValue;
		this.ignoreAdjacent = ignoreAdjacent;
	}

	/**
	 * Constructor to add a dice
	 * @param dice to add
	 */
	DicePlacementCondition(Dice dice)
	{
		this.dice = dice;
		this.ignoreColor = false;
		this.ignoreValue = false;
		this.ignoreAdjacent = false;
	}

	/**
	 * Set ignore Color constraints
	 * @param ignoreCol boolean
	 * @return new DicePlacementCondition (the previous with new ignoreColor value)
	 */
	DicePlacementCondition setIgnoreColor(boolean ignoreCol)
	{
		return new DicePlacementCondition(dice, ignoreCol, ignoreValue, ignoreAdjacent);
	}

	/**
	 * Set ignore Value constraints
	 * @param ignoreVal boolean
	 * @return new DicePlacementCondition (the previous with new ignoreValue value)
	 */
	DicePlacementCondition setIgnoreValue(boolean ignoreVal)
	{
		return new DicePlacementCondition(dice, ignoreColor, ignoreVal, ignoreAdjacent);
	}

	/**
	 * Set ignore Adjacent constraints
	 * @param ignoreAdj boolean
	 * @return new DicePlacementCondition (the previous with new ignoreAdjacent value)
	 */
	DicePlacementCondition setIgnoreAdjacent(boolean ignoreAdj)
	{
		return new DicePlacementCondition(dice, ignoreColor, ignoreValue, ignoreAdj);
	}

	/**
	 * Change dice
	 * @param dice dice to set
	 * @return new DicePlacementCondition (the previous with new ignoreAdjacent value)
	 */
	public DicePlacementCondition changeDice(Dice dice)
	{
		return new DicePlacementCondition(dice, ignoreColor, ignoreValue, ignoreAdjacent);
	}

	/**
	 * Get dice
	 * @return dice
	 */
	public Dice getDice()
	{
		return dice;
	}

	/**
	 * Get ignore Color constraints
	 * @return ignore Color
	 */
	public Boolean getIgnoreColor()
	{
		return ignoreColor;
	}

	/**
	 * Get ignore Value constraints
	 * @return ignore Value
	 */
	public Boolean getIgnoreValue()
	{
		return ignoreValue;
	}

	/**
	 * Get ignore Adjacent constraints
	 * @return ignore Adjacent
	 */
	public Boolean getIgnoreAdjacent()
	{
		return ignoreAdjacent;
	}

	/**
	 * Method to verify if the dice can be placed in the selected position respecting the selected constraints
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param playerBoard player board
	 * @return boolean (true: the dice can be placed, false: the dice can't be placed)
	 */
	public boolean canBePlaced(int y, int x, PlayerBoard playerBoard)
	{
		WindowFrame windowFrame = playerBoard.getData().getWindowFrame();
		DicePlacedFrame dicePlacedFrame = playerBoard.getDicePlacedFrame();

		boolean ok = true;

		if(!verifyPositions(y, x))          //Verify if x and y are valid positions
		{
			LOGGER.log(Level.FINE, "Wrong x and/or y");
			ok = false;
		}

		else if(dicePlacedFrame.getData().getDice(y, x)!=null)    //Verify if the position y, x is free
		{
			LOGGER.log(Level.FINE, "Only one dice in a position");
			ok = false;
		}

		else if(!verifyColor(y, x, windowFrame))        //Verify color bond
		{
			LOGGER.log(Level.FINE, "Color bond not respected");
			ok = false;
		}

		else if(!verifyFirstDiceEdge(y, x, dicePlacedFrame))      //Verify first dice is positioned near the edge
		{
			LOGGER.log(Level.FINE, "The first dice must be positioned near the edge");
			ok = false;
		}

		else if(!verifyValue(y, x, windowFrame))        //Verify value bond
		{
			LOGGER.log(Level.FINE, "Value bond not respected");
			ok = false;
		}

		else if(dicePlacedFrame.getData().getNDices()!=0 && !checkAdjacent(y, x, dicePlacedFrame))                //Verify the dice is positioned near an other dice (if it is not the first dice)
		{
			LOGGER.log(Level.FINE, "The dice must be positioned near an other dice");
			ok = false;
		}

		else if(!checkNearValue(y, x, dicePlacedFrame))                        //Verify the dice is not positioned near a dice with the same value or color
		{
			LOGGER.log(Level.FINE, "The dice can't be positioned near a dice with the same value");
			ok = false;
		}

		else if(!checkNearColor(y, x, dicePlacedFrame))                        //Verify the dice is not positioned near a dice with the same value or color
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

	/**
	 * Support method to verify if selected position is correct
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return boolean (true: position is correct, false: position is not correct)
	 */
	private boolean verifyPositions(int y, int x)
	{
		return(!(x<0 || x>X_MAX || y<0 || y>Y_MAX));
	}

	/**
	 * Support method to verify if Color constraints are respected
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param windowFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean verifyColor(int y, int x, WindowFrame windowFrame)
	{
		if(ignoreColor)
		{
			return true;
		}
		return(!(windowFrame.getColorBond(y, x)!=null && windowFrame.getColorBond(y, x)!=dice.getGameColor()));
	}

	/**
	 * Support method to verify if Value constraints are respected
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param windowFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean verifyValue(int y, int x, WindowFrame windowFrame)
	{
		if(ignoreValue)
		{
			return true;
		}
		return(!(windowFrame.getValueBond(y, x)!=null && windowFrame.getValueBond(y, x)!=dice.getValue()));
	}

	/**
	 * Support method to verify the first dice is positioned near the edge
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean verifyFirstDiceEdge(int y, int x, DicePlacedFrame dicePlacedFrame)
	{
		if(dicePlacedFrame.getData().getNDices()==0)
		{
			return (x==0 || x==X_MAX || y==0 || y==Y_MAX);
		}
		return true;
	}

	/**
	 * Support method to verify if Adjacent constraints are respected
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean checkAdjacent(int y, int x, DicePlacedFrame dicePlacedFrame)
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
			if(verifyNear(y + c.getDy(), x + c.getDx(), dicePlacedFrame))
			{
				found = true;
			}
		}

		return found;
	}

	/**
	 * Support method to verify if near value constraints are respected
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean checkNearValue(int y, int x, DicePlacedFrame dicePlacedFrame)
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
			if(!verifyNearValue(y + c.getDy(), x + c.getDx(), dicePlacedFrame))
			{
				found = false;
			}
		}

		return found;
	}

	/**
	 * Support method to verify if near color constraints are respected
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean checkNearColor(int y, int x, DicePlacedFrame dicePlacedFrame)
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
			if(!verifyNearColor(y + c.getDy(), x + c.getDx(), dicePlacedFrame))
			{
				found = false;
			}
		}

		return found;
	}

	/**
	 * Support method to verify if new dice is near an other dice
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean verifyNear(int y, int x, DicePlacedFrame dicePlacedFrame)
	{
		if(x<0 || y<0 || x>X_MAX || y>Y_MAX)
		{
			return false;
		}
		return(dicePlacedFrame.getData().getDice(y, x)!=null);
	}

	/**
	 * Support method to verify if new dice is near an other dice with same Value
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean verifyNearValue(int y, int x, DicePlacedFrame dicePlacedFrame)
	{
		if(x<0 || y<0 || x>X_MAX || y>Y_MAX)
		{
			return true;
		}
		return(!(dicePlacedFrame.getData().getDice(y, x)!=null && (dice.getValue()==dicePlacedFrame.getData().getDice(y, x).getValue())));
	}

	/**
	 * Support method to verify if new dice is near an other dice with same Color
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @param dicePlacedFrame actual dice placed frame
	 * @return boolean
	 */
	private boolean verifyNearColor(int y, int x, DicePlacedFrame dicePlacedFrame)
	{
		if(x<0 || y<0 || x>X_MAX || y>Y_MAX)
		{
			return true;
		}
		return (!(dicePlacedFrame.getData().getDice(y, x)!=null && (dice.getGameColor()==dicePlacedFrame.getData().getDice(y, x).getGameColor())));
	}

}
