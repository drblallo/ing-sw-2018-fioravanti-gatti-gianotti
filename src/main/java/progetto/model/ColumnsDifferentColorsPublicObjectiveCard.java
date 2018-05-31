package progetto.model;

/**
 * Class for public objective card "Colori diversi - colonna"
 */
public class ColumnsDifferentColorsPublicObjectiveCard extends AbstractColumnsRowsDifferentColorsValuesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	ColumnsDifferentColorsPublicObjectiveCard()
	{
		super("Colori diversi - colonna", "Colonne senza colori ripetuti");
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
