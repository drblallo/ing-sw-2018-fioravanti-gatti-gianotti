package progetto.model;

/**
 * Abstract class for public objective card
 * @author Michele
 */
public abstract class AbstractPublicObjectiveCard extends AbstractObjectiveCard {

	/**
	 * Constructor
	 * @param name name of the card
	 * @param effect effect of the card
	 * @param cardID ID of the card
	 */
	AbstractPublicObjectiveCard(String name, String effect, int cardID)
	{
		super(name, effect, cardID);
	}

}
