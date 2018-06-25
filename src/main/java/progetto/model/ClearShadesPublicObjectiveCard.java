package progetto.model;

/**
 * Class for public objective card "Sfumature Chiare"
 */
public class ClearShadesPublicObjectiveCard extends AbstractValueShadesPublicObjectiveCard {

	private static final int CARD_ID = 4;

	/**
	 * Constructor
	 */
	ClearShadesPublicObjectiveCard()
	{
		super("Sfumature Chiare", "Set di 1 & 2 ovunque", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, Value.ONE, Value.TWO);
	}

}
