package progetto.model;

/**
 * Class for private objective card "Sfumature"
 */
public class ColorShadesPrivateObjectiveCard extends AbstractPrivateObjectiveCard {

	private Color color;

	/**
	 * Constructor
	 * @param color
	 */
	ColorShadesPrivateObjectiveCard(Color color)
	{
		super("Sfumature " + color.toString(), "Somma dei valori su tutti i dadi " + color.toString());
		this.color = color;
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		DicePlacedFrameData dicePlacedFrameData = dicePlacedFrame.getData();

		int result = 0;
		Dice dice;

		for(int i=0; i<MAX_NUMBER_OF_ROWS; i++)
		{
			for(int j=0; j<MAX_NUMBER_OF_COLUMNS; j++)
			{
				dice = dicePlacedFrameData.getDice(i, j);
				if(dice!=null && dice.getColor() == color)
				{
					result = result + dice.getValue().ordinal()+1;
				}
			}
		}

		return result;

	}

}
