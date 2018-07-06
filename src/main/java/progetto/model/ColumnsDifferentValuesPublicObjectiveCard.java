package progetto.model;

/**
 * Class for public objective card "Sfumature diverse - colonna"
 */
public class ColumnsDifferentValuesPublicObjectiveCard extends AbstractDifferentColValPublicObjectiveCard {

	private static final int CARD_ID = 3;

	/**
	 * Constructor
	 */
	ColumnsDifferentValuesPublicObjectiveCard()
	{
		super("Sfumature diverse - colonna", "Colonne senza sfumature ripetute", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, false, true);
	}

}
