package progetto.game;

/**
 * Class for public objective card "Sfumature Scure"
 */
public class DarkShadesPublicObjectiveCard extends AbstractValueShadesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	DarkShadesPublicObjectiveCard()
	{
		super("Sfumature Scure", "Set di 4 & 5 ovunque");
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
