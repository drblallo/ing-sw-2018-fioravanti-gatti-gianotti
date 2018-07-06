package progetto.model;

/**
 * Class for public objective card "Sfumature Medie"
 * @author Michele
 */
public class MediumShadesPublicObjectiveCard extends AbstractValueShadesPublicObjectiveCard {

	private static final int CARD_ID = 5;

	/**
	 * Constructor
	 */
	MediumShadesPublicObjectiveCard()
	{
		super("Sfumature Medie", "Set di 3 & 4 ovunque", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, Value.THREE, Value.FOUR);
	}

}
