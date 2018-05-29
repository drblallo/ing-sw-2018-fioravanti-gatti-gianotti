package progetto.game;

/**
 * Class for public objective card "Sfumature Chiare"
 */
public class ClearShadesPublicObjectiveCard extends AbstractValueShadesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	ClearShadesPublicObjectiveCard()
	{
		super("Sfumature Chiare", "Set di 1 & 2 ovunque");
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
