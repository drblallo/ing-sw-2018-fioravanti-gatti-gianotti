package progetto.model;

/**
 * Abstract class for private objective card
 * @author Michele
 */
public abstract class AbstractPrivateObjectiveCard extends AbstractObjectiveCard {

	/**
	 * Constructor
	 * @param name
	 * @param effect
	 * @param cardID
	 */
	AbstractPrivateObjectiveCard(String name, String effect, int cardID)
	{
		super(name, effect, cardID);
	}

}
