package progetto.game;

public class RoughingForcepsToolCard extends AbstractToolCard {

	/**
	 * Constructor with name, effect and color of the card
	 */
	RoughingForcepsToolCard()
	{
		super("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", Color.PURPLE);
	}

	@Override
	public AbstractGameState getState()
	{
		return new RoughingForcepsState();
	}

}
