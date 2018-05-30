package progetto.game;

import java.io.Serializable;

public abstract class AbstractToolCard implements Serializable {

	private String name;
	private String effect;
	private Color color;

	/**
	 * Constructor with name, effect and color of the card
	 * @param name name of the card
	 * @param effect effect of the card
	 */
	AbstractToolCard(String name, String effect, Color color)
	{
		this.name = name;
		this.effect = effect;
		this.color = color;
	}

	/**
	 * Get tool tip
	 * @return tool tip (name and effect)
	 */
	public String getToolTip()
	{
		//return card name and effect description
		return name + " " + effect + " " + color;
	}

	public abstract AbstractGameState getState();

}