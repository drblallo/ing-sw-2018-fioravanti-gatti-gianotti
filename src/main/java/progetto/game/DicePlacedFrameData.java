package progetto.game;

public final class DicePlacedFrameData {

	private static final int MAX_NUMBER_OF_ROWS = 4;
	private static final int MAX_NUMBER_OF_COLUMNS = 5;

	private final Dice[][] dicesFrame = new Dice[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];
	private final int nPlacedDices;

	DicePlacedFrameData()
	{
		nPlacedDices = 0;
	}

	DicePlacedFrameData(DicePlacedFrameData dicePlacedFrameData, Dice newDice, int x, int y)
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

	DicePlacedFrameData(DicePlacedFrameData dicePlacedFrameData, int x, int y)
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


	public Dice getDice(int x, int y)
	{
		return dicesFrame[x][y];
	}

	public int getNDices()
	{
		return nPlacedDices;
	}

	DicePlacedFrameData addDice(Dice newDice, int x, int y)
	{
		return new DicePlacedFrameData(this, newDice, x, y);
	}

	DicePlacedFrameData removeDice(int x, int y)
	{
		return new DicePlacedFrameData(this, x, y);
	}

}
