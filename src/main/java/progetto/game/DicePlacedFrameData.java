package progetto.game;

/**
 * Immutable class with data of DicePlacedFrame
 */
public final class DicePlacedFrameData {

	public static final int MAX_NUMBER_OF_ROWS = 4;
	public static final int MAX_NUMBER_OF_COLUMNS = 5;

	private final Dice[][] dicesFrame = new Dice[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];
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
	private DicePlacedFrameData(DicePlacedFrameData dicePlacedFrameData, Dice newDice, int x, int y)
	{
		for (int i = 0; i < MAX_NUMBER_OF_COLUMNS; i++)
		{
			for (int j = 0; j < MAX_NUMBER_OF_ROWS; j++)
			{
				dicesFrame[i][j] = dicePlacedFrameData.dicesFrame[i][j];
			}
		}
		dicesFrame[x][y]=newDice;
		nPlacedDices = dicePlacedFrameData.nPlacedDices+1;
	}

	/**
	 * Constructor to remove a dice from the dice placed frame
	 * @param dicePlacedFrameData to copy
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	private DicePlacedFrameData(DicePlacedFrameData dicePlacedFrameData, int x, int y)
	{
		for (int i = 0; i < MAX_NUMBER_OF_COLUMNS; i++)
		{
			for (int j = 0; j < MAX_NUMBER_OF_ROWS; j++)
				if (dicePlacedFrameData.dicesFrame[i][j] != null)
				{
					dicesFrame[i][j] = dicePlacedFrameData.dicesFrame[i][j];
				}
		}
		dicesFrame[x][y]=null;
		nPlacedDices = dicePlacedFrameData.nPlacedDices-1;
	}

	/**
	 * Get dice
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return dice in the selected position
	 */
	public Dice getDice(int x, int y)
	{
		return dicesFrame[x][y];
	}

	/**
	 *
	 * @return number of dice in the placed frame
	 */
	public int getNDices()
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
	DicePlacedFrameData addDice(Dice newDice, int x, int y)
	{
		return new DicePlacedFrameData(this, newDice, x, y);
	}

	/**
	 * Remove a dice from the placed frame
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return new DicePlacementFrameData (the previous one without the removed dice)
	 */
	DicePlacedFrameData removeDice(int x, int y)
	{
		return new DicePlacedFrameData(this, x, y);
	}

}
