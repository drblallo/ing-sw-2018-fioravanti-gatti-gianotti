package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToolCard implements Serializable {

	private String name;
	private String effect;
	private GameColor gameColor;
	private int index;
	private List<Class> cardAction;

	/**
	 * Constructor with name, effect and color of the card
	 * @param name name of the card
	 * @param effect effect of the card
	 */
	ToolCard(String name, String effect, GameColor gameColor, int index, List<Class> cardAction)
	{
		this.name = name;
		this.effect = effect;
		this.gameColor = gameColor;
		this.index = index;
		this.cardAction = cardAction;
	}

	/**
	 * Get tool tip
	 * @return tool tip (name and effect)
	 */
	public String getToolTip()
	{
		//return card name and effect description
		return index + " " + gameColor + " " + name + " " + effect;
	}

	public int getIndex()
	{
		return index;
	}

	public GameColor getGameColor() {
		return gameColor;
	}

	public List<Class> getCardAction()
	{
		return new ArrayList<>(cardAction);
	}
}