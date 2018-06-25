package progetto.model;

/**
 * Class for public objective card "Sfumature Scure"
 */
public class DarkShadesPublicObjectiveCard extends AbstractValueShadesPublicObjectiveCard {

	private static final int CARD_ID = 6;

	/**
	 * Constructor
	 */
	DarkShadesPublicObjectiveCard()
	{
		super("Sfumature Scure", "Set di 4 & 5 ovunque", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, Value.FOUR, Value.FIVE);
	}

}
