package progetto.model;

/**
 * Class for public objective card "Varieta' di colore"
 */
public class DifferentColorsPublicObjectiveCard extends AbstractPublicObjectiveCard {

	private static final int N_COLORS = 5;
	private static final int N_POINT = 4;

	/**
	 * Constructor
	 */
	DifferentColorsPublicObjectiveCard() {
		super("Varieta' di colore", "Set di dadi di ogni colore ovunque");
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
		int[] nValues = new int[N_COLORS];
		int min;

		for(int i=0; i<MAX_NUMBER_OF_ROWS; i++)
		{
			for(int j=0; j<MAX_NUMBER_OF_COLUMNS; j++)
			{
				dice = dicePlacedFrameData.getDice(i, j);
				if(dice!=null)
				{
					nValues[dice.getColor().ordinal()]++;
				}
			}
		}

		min=nValues[0];

		for(int i=1; i<N_COLORS; i++)
		{
			if(nValues[i]<min)
			{
				min=nValues[i];
			}
		}

		return min*N_POINT;

	}
}
