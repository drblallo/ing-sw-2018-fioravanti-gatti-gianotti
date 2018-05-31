package progetto.model;

/**
 * Class for public objective card "Sfumature Medie"
 */
public class MediumShadesPublicObjectiveCard extends AbstractValueShadesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	MediumShadesPublicObjectiveCard()
	{
		super("Sfumature Medie", "Set di 3 & 4 ovunque");
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
