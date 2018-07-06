package progetto.model;

import java.io.Serializable;

/**
 * Window frame for Window Pattern Card
 * @author Michele
 */
public final class WindowFrame implements Serializable {

	public static final int MAX_NUMBER_OF_ROWS = 4;
	public static final int MAX_NUMBER_OF_COLUMNS = 5;

	private final Value[][] valueLimitationMatrix = new Value[MAX_NUMBER_OF_ROWS][MAX_NUMBER_OF_COLUMNS];
	private final GameColor[][] colorLimitationMatrix = new GameColor[MAX_NUMBER_OF_ROWS][MAX_NUMBER_OF_COLUMNS];
	private final int favorToken;
	private final String name;

	/**
	 * Constructor
	 */
	WindowFrame()
	{
		favorToken=0;
		name="";
	}

	/**
	 * Get number of favor token of the card
	 * @return number of favor token of the card
	 */
	public int getFavorToken()
	{
		return favorToken;
	}

	/**
	 * Get name of the card
	 * @return the name of the card
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get color bond of the card in the selected position
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return Color bond of the card in the selected position
	 */
	public GameColor getColorBond(int y, int x)
	{
		return colorLimitationMatrix[y][x];
	}

	/**
	 * Get Value bond of the card in the selected position
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return Value bond of the card in the selected position
	 */
	public Value getValueBond(int y, int x)
	{
		return valueLimitationMatrix[y][x];
	}


}