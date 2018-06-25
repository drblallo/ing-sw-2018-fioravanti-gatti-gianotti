package progetto.model;

/**
 * Class for public objective card "Colori diversi - riga"
 */
public class RowsDifferentColorsPublicObjectiveCard extends AbstractColumnsRowsDifferentColorsValuesPublicObjectiveCard {

	private static final int CARD_ID = 0;

	/**
	 * Constructor
	 */
	RowsDifferentColorsPublicObjectiveCard()
	{
		super("Colori diversi - riga", "Righe senza colori ripetuti", CARD_ID);
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame)
	{
		return super.evaluateFrame(dicePlacedFrame, true, false);
	}

}
