package progetto.model;

import java.io.Serializable;

/**
 * Class tool card
 * @author Michele
 */
public class ToolCard implements Serializable {

	private String name;
	private String effect;
	private GameColor gameColor;
	private int index;

	/**
	 * Constructor with name, effect and color of the card
	 * @param name name of the card
	 * @param effect effect of the card
	 */
	ToolCard(String name, String effect, GameColor gameColor, int index)
	{
		this.name = name;
		this.effect = effect;
		this.gameColor = gameColor;
		this.index = index;

	}

	/**
	 * Get tool tip
	 * @return tool tip (name and effect)
	 */
	public String getToolTip()
	{
		//return card name and effect description
		return "Colore: " + gameColor + "\nNome: " + name + "\nEffetto: " + effect + "\n";
	}

	public int getIndex()
	{
		return index;
	}

	public GameColor getGameColor() {
		return gameColor;
	}

}