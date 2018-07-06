package progetto.model;

/**
 * Abstract class for public objective card where is asked to count different value/color in columns/rows
 * @author Michele
 */
public abstract class AbstractDifferentColValPublicObjectiveCard extends AbstractPublicObjectiveCard{

	private static final int N_PUNT_COL_COL = 5;
	private static final int N_PUNT_COL_VAL = 4;
	private static final int N_PUNT_ROW_COL = 6;
	private static final int N_PUNT_ROW_VAL = 5;

	/**
	 * Constructor
	 * @param name name of the card
	 * @param effect effect of the card
	 * @param cardID ID of the card
	 */
	AbstractDifferentColValPublicObjectiveCard(String name, String effect, int cardID) {
		super(name, effect, cardID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame the dice placed frame to evaluate
	 * @param rowsColumns true = rows , false = columns
	 * @param valuesColors true = values, false = colors
	 * @return n point
	 */
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame, boolean rowsColumns, boolean valuesColors)
	{
		DicePlacedFrameData dicePlacedFrameData = dicePlacedFrame.getData();
		if(rowsColumns)
		{
			return evaluateRows(dicePlacedFrameData, valuesColors);
		}
		else
		{
			return evaluateColumns(dicePlacedFrameData, valuesColors);
		}
	}

	/**
	 * Support class to evaluate rows
	 * @param dicePlacedFrameData dice placed frame to evaluate
	 * @param valuesColors true = values, false = colors
	 * @return points of the rows
	 */
	private int evaluateRows(DicePlacedFrameData dicePlacedFrameData, boolean valuesColors)
	{
		int result = 0;
		for(int i=0; i<MAX_NUMBER_OF_COLUMNS; i++)
		{
			if(evaluateRow(dicePlacedFrameData, i, valuesColors) && valuesColors)
			{
				result = result + N_PUNT_ROW_VAL;
			}
			else if(evaluateRow(dicePlacedFrameData, i, valuesColors) && !valuesColors) {
				result = result + N_PUNT_ROW_COL;
			}
		}

		return result;
	}

	/**
	 * Support class to evaluate columns
	 * @param dicePlacedFrameData dice placed frame to evaluate
	 * @param valuesColors  true = values, false = colors
	 * @return points of the columns
	 */
	private int evaluateColumns(DicePlacedFrameData dicePlacedFrameData, boolean valuesColors)
	{
		int result = 0;
		for(int i=0; i<MAX_NUMBER_OF_ROWS; i++)
		{
			if(evaluateColumn(dicePlacedFrameData, i, valuesColors) && valuesColors)
			{
				result = result + N_PUNT_COL_VAL;
			}
			else if(evaluateColumn(dicePlacedFrameData, i, valuesColors) && !valuesColors) {
				result = result + N_PUNT_COL_COL;
			}
		}
		return result;
	}

	/**
	 * Support class to evaluate row
	 * @param dicePlacedFrameData dice placed frame to evaluate
	 * @param row number of the row
	 * @param valuesColors true = values, false = colors
	 * @return points of the row
	 */
	private boolean evaluateRow(DicePlacedFrameData dicePlacedFrameData, int row, boolean valuesColors)
	{
		Dice[] dice = new Dice[MAX_NUMBER_OF_COLUMNS];
		for(int j=0; j<MAX_NUMBER_OF_COLUMNS; j++)
		{
			dice[j] = dicePlacedFrameData.getDice(row, j);
		}
		for(int j=0; j<MAX_NUMBER_OF_COLUMNS-1; j++)
		{
			for(int z=j+1; z<MAX_NUMBER_OF_COLUMNS; z++)
			{
				if(dice[j]==null || dice[z]==null||
						(valuesColors && dice[z].getValue()==dice[j].getValue()) ||
						(!valuesColors && dice[z].getGameColor()==dice[j].getGameColor()))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Support class to evaluate columns
	 * @param dicePlacedFrameData dice placed frame to evaluate
	 * @param column number of the column
	 * @param valuesColors true = values, false = colors
	 * @return points of the column
	 */
	private boolean evaluateColumn(DicePlacedFrameData dicePlacedFrameData, int column, boolean valuesColors)
	{
		Dice[] dice = new Dice[MAX_NUMBER_OF_ROWS];
		for(int j=0; j<MAX_NUMBER_OF_ROWS; j++)
		{
			dice[j] = dicePlacedFrameData.getDice(j, column);
		}
		for(int j=0; j<MAX_NUMBER_OF_ROWS-1; j++)
		{
			for(int z=j+1; z<MAX_NUMBER_OF_ROWS; z++)
			{
				if(dice[j]==null || dice[z]==null||
						(valuesColors && dice[z].getValue()==dice[j].getValue()) ||
						(!valuesColors && dice[z].getGameColor()==dice[j].getGameColor()))
				{
					return false;
				}
			}
		}
		return true;
	}
}
