package progetto.model;

/**
 * Class for public objective card "Colori diversi - colonna"
 * @author Michele
 */
public class ColumnsDifferentColorsPublicObjectiveCard extends AbstractDifferentColValPublicObjectiveCard {

	private static final int CARD_ID = 1;

	/**
	 * Constructor
	 */
	ColumnsDifferentColorsPublicObjectiveCard()
	{
		super("Colori diversi - colonna", "Colonne senza colori ripetuti", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, false, false);
	}
}
