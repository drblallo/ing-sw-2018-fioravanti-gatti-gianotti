package progetto.model;

/**
 * Abstract class for public objective card where is asked to count value shades
 */
public abstract class AbstractValueShadesPublicObjectiveCard extends  AbstractPublicObjectiveCard{

	private static final int N_POINT = 2;

	/**
	 * Constructor
	 * @param name
	 * @param effect
	 */
	AbstractValueShadesPublicObjectiveCard(String name, String effect) {
		super(name, effect);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame
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
