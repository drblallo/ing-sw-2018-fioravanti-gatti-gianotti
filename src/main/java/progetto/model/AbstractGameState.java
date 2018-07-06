package progetto.model;

import java.io.Serializable;

/**
 * Class that states must extend
 * @author Michele
 */
public abstract class AbstractGameState implements Serializable
{
	private final String name;

	/**
	 * public constructor
	 * @param stateName name of the state
	 */
	public AbstractGameState(String stateName)
	{
		name = stateName;
	}

	/**
	 *
	 * @return the name of the state
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method is called every time this state is set as the current state
	 * @param game game where this state was set as current state
	 */
	void apply(Model game) {}

}
