package progetto.game;

/**
 * Class for public objective card "Sfumature diverse - riga"
 */
public class RowsDifferentValuesPublicObjectiveCard extends AbstractColumnsRowsDifferentColorsValuesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	RowsDifferentValuesPublicObjectiveCard()
	{
		super("Sfumature diverse - riga", "Righe senza sfumature ripetute");
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, true, true);
	}

}
