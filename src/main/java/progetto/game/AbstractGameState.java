package progetto.game;

public abstract class AbstractGameState
{
	private final String name;

	public AbstractGameState(String stateName)
	{
		name = stateName;
	}

	public String getName() {
		return name;
	}
}
