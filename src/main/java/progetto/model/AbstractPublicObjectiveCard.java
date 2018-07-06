package progetto.model;

/**
 * Abstract class for public objective card
 * @author Michele
 */
public abstract class AbstractPublicObjectiveCard extends AbstractObjectiveCard {

	/**
	 * Constructor
	 * @param name
	 * @param effect
	 * @param cardID
	 */
	AbstractPublicObjectiveCard(String name, String effect, int cardID)
	{
		super(name, effect, cardID);
	}

}
