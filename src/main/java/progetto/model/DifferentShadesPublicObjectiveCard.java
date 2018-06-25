package progetto.model;

/**
 * Class for public objective card "Sfumature Diverse"
 */
public class DifferentShadesPublicObjectiveCard extends AbstractPublicObjectiveCard {

	private static final int N_VALUES = 6;
	private static final int N_POINT = 5;
	private static final int CARD_ID = 7;

	/**
	 * Constructor
	 */
	DifferentShadesPublicObjectiveCard() {
		super("Sfumature Diverse", "Set di dadi di ogni valore ovunque", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame) {

		DicePlacedFrameData dicePlacedFrameData = dicePlacedFrame.getData();

		Dice dice;
		int[] nValues = new int[N_VALUES];
		int min;

		for(int i=0; i<MAX_NUMBER_OF_ROWS; i++)
		{
			for(int j=0; j<MAX_NUMBER_OF_COLUMNS; j++)
			{
				dice = dicePlacedFrameData.getDice(i, j);
				if(dice!=null)
				{
					nValues[dice.getValue().ordinal()]++;
				}
			}
		}

		min=nValues[0];

		for(int i=1; i<N_VALUES; i++)
		{
			if(nValues[i]<min)
			{
				min=nValues[i];
			}
		}

		return min*N_POINT;

	}
}
