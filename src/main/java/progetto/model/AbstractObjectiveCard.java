package progetto.model;

import java.io.Serializable;

/**
 * Abstract class for objective card
 */
public abstract class AbstractObjectiveCard implements Serializable {

	private String name;
	private String effect;
	private int cardID;

	public static final int MAX_NUMBER_OF_ROWS = 4;
	public static final int MAX_NUMBER_OF_COLUMNS = 5;

	/**
	 * Constructor with name and effect of the card
	 * @param name name of the card
	 * @param effect effect of the card
	 * @param cardID id of the card
	 */
	AbstractObjectiveCard(String name, String effect, int cardID)
	{
		this.name = name;
		this.effect = effect;
		this.cardID = cardID;
	}

	/**
	 * Get tool tip
	 * @return tool tip (name and effect)
	 */
	public String getToolTip()
	{
		//return card name and effect description
		return name + " " + effect;
	}

	/**
	 * Method to evaluate dicePlacedFrame using the selected objective card
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	public abstract int evaluateFrame(DicePlacedFrame dicePlacedFrame);

	/**
	 * Get card ID
	 * @return card ID
	 */
	public int getCardID()
	{
		return cardID;
	}


}
