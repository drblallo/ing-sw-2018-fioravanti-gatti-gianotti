package progetto.game;

/**
 * Class for public objective card "Colori diversi - riga"
 */
public class RowsDifferentColorsPublicObjectiveCard extends AbstractColumnsRowsDifferentColorsValuesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	RowsDifferentColorsPublicObjectiveCard()
	{
		super("Colori diversi - riga", "Righe senza colori ripetuti");
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
