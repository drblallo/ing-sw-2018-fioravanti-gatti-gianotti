package progetto.game;

/**
 * Immutable class with data of DicePlacedFrame
 */
public final class DicePlacedFrameData {

	private static final int MAX_NUMBER_OF_ROWS = 4;
	private static final int MAX_NUMBER_OF_COLUMNS = 5;

	private final Dice[][] dicesFrame = new Dice[MAX_NUMBER_OF_ROWS][MAX_NUMBER_OF_COLUMNS];
	private final int nPlacedDices;

	/**
	 * Constructor with 0 dices, empty dice placed frame
	 */
	DicePlacedFrameData()
	{
		nPlacedDices = 0;
	}

	/**
	 * Constructor to add a new dice
	 * @param dicePlacedFrameData to copy
	 * @param newDice to add in the placed frame
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	private DicePlacedFrameData(DicePlacedFrameData dicePlacedFrameData, Dice newDice, int y, int x)
	{
		for (int i = 0; i < MAX_NUMBER_OF_ROWS; i++)
		{
			for (int j = 0; j < MAX_NUMBER_OF_COLUMNS; j++)
			{
				dicesFrame[i][j] = dicePlacedFrameData.dicesFrame[i][j];
			}
		}
		dicesFrame[y][x]=newDice;
		nPlacedDices = dicePlacedFrameData.nPlacedDices+1;
	}

	/**
	 * Constructor to remove a dice from the dice placed frame
	 * @param dicePlacedFrameData to copy
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	private DicePlacedFrameData(DicePlacedFrameData dicePlacedFrameData, int y, int x)
	{
		for (int i = 0; i < MAX_NUMBER_OF_ROWS; i++)
		{
			for (int j = 0; j < MAX_NUMBER_OF_COLUMNS; j++)
				if (dicePlacedFrameData.dicesFrame[i][j] != null)
				{
					dicesFrame[i][j] = dicePlacedFrameData.dicesFrame[i][j];
				}
		}
		dicesFrame[y][x]=null;
		nPlacedDices = dicePlacedFrameData.nPlacedDices-1;
	}

	/**
	 * Get dice
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return dice in the selected position
	 */
	Dice getDice(int y, int x)
	{
		if(x<0 || x>MAX_NUMBER_OF_COLUMNS-1 || y<0 || y>MAX_NUMBER_OF_ROWS-1)
		{
			return null;
		}
		return dicesFrame[y][x];
	}

	/**
	 *
	 * @return number of dice in the placed frame
	 */
	int getNDices()
	{
		return nPlacedDices;
	}

	/**
	 * Add a dice in the placed frame
	 * @param newDice dice to add
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return new DicePlacementFrameData (the previous one with the added dice)
	 */
	DicePlacedFrameData addDice(Dice newDice, int y, int x)
	{
		if(x<0 || x>MAX_NUMBER_OF_COLUMNS-1 || y<0 || y>MAX_NUMBER_OF_ROWS-1 || getDice(y, x)!=null)
		{
			return this;
		}
		return new DicePlacedFrameData(this, newDice, y, x);

	}

	/**
	 * Remove a dice from the placed frame
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return new DicePlacementFrameData (the previous one without the removed dice)
	 */
	DicePlacedFrameData removeDice(int y, int x)
	{
		if(x<0 || x>MAX_NUMBER_OF_COLUMNS-1 || y<0 || y>MAX_NUMBER_OF_ROWS-1)
		{
			return this;
		}
		return new DicePlacedFrameData(this, y, x);
	}

}
