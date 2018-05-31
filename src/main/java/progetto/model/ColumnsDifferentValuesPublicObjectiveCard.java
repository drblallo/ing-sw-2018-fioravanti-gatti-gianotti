package progetto.model;

/**
 * Class for public objective card "Sfumature diverse - colonna"
 */
public class ColumnsDifferentValuesPublicObjectiveCard extends AbstractColumnsRowsDifferentColorsValuesPublicObjectiveCard {

	/**
	 * Constructor
	 */
	ColumnsDifferentValuesPublicObjectiveCard()
	{
		super("Sfumature diverse - colonna", "Colonne senza sfumature ripetute");
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
