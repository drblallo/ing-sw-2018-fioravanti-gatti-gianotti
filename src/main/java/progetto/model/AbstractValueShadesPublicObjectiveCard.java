package progetto.model;

/**
 * Abstract class for public objective card where is asked to count value shades
 * @author Michele
 */
public abstract class AbstractValueShadesPublicObjectiveCard extends  AbstractPublicObjectiveCard{

	private static final int N_POINT = 2;

	/**
	 * Constructor
	 * @param name name of the card
	 * @param effect effect of the card
	 * @param cardID ID of the card
	 */
	AbstractValueShadesPublicObjectiveCard(String name, String effect, int cardID) {
		super(name, effect, cardID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame to evaluate
	 * @param value1 value 1 to find in dicePlacedFrame
	 * @param value2 value 2 to find in dicePlacedFrame
	 * @return n point
	 */
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame, Value value1, Value value2)
	{
		DicePlacedFrameData dicePlacedFrameData = dicePlacedFrame.getData();

		Dice dice;
		int nValue1 = 0;
		int nValue2 = 0;

		for(int i=0; i<MAX_NUMBER_OF_ROWS; i++)
		{
			for(int j=0; j<MAX_NUMBER_OF_COLUMNS; j++)
			{
				dice = dicePlacedFrameData.getDice(i, j);
				if(dice!=null && dice.getValue()==value1)
				{
					nValue1++;
				}
				else if(dice!=null && dice.getValue()==value2)
				{
					nValue2++;
				}
			}
		}

		return (Math.min(nValue1, nValue2))*N_POINT;

	}

}
