package progetto.model;

/**
 * Abstract class for private objective card
 * @author Michele
 */
public abstract class AbstractPrivateObjectiveCard extends AbstractObjectiveCard {

	/**
	 * Constructor
	 * @param name name of the card
	 * @param effect effect of the card
	 * @param cardID ID of the card
	 */
	AbstractPrivateObjectiveCard(String name, String effect, int cardID)
	{
		super(name, effect, cardID);
	}

}
