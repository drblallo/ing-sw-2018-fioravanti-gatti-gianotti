package progetto.model;

/**
 * Class for public objective card "Sfumature diverse - riga"
 * @author Michele
 */
public class RowsDifferentValuesPublicObjectiveCard extends AbstractDifferentColValPublicObjectiveCard {

	private static final int CARD_ID = 2;

	/**
	 * Constructor
	 */
	RowsDifferentValuesPublicObjectiveCard()
	{
		super("Sfumature diverse - riga", "Righe senza sfumature ripetute", CARD_ID);
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
