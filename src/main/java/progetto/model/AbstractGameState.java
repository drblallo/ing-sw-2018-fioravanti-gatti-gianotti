package progetto.model;

import java.io.Serializable;

public abstract class AbstractGameState implements Serializable
{
	private final String name;

	public AbstractGameState(String stateName)
	{
		name = stateName;
	}

	public String getName() {
		return name;
	}

	void apply(Game game) {}

}
