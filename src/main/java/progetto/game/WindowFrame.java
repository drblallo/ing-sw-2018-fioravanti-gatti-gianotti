package progetto.game;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Window frame for Window Pattern Card. Upload by file using JSON - JSONArray.
 */
public final class WindowFrame implements Serializable {

	private static final int MAX_NUMBER_OF_ROWS = 4;
	private static final int MAX_NUMBER_OF_COLUMNS = 5;

	private final Value[][] valueLimitationMatrix = new Value[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];
	private final Color[][] colorLimitationMatrix = new Color[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];
	private final int favorToken;
	private final String name;

	/**
	 * Constructor
	 * @param frame JSONArray of the window frame to set
	 */
	WindowFrame(JSONArray frame)
	{
		int pos=0;

		int nVincoli;
		int x;
		int y;

		name = frame.getString(pos);
		pos++;

		favorToken=frame.getInt(pos);
		pos++;

		nVincoli = frame.getInt(pos);       //Vincoli valore
		pos++;

		for(int i=0; i<nVincoli; i++)
		{
			x = frame.getInt(pos);
			pos++;

			y = frame.getInt(pos);
			pos++;

			valueLimitationMatrix[x][y]=(Value)frame.get(pos);
			pos++;
		}

		nVincoli = frame.getInt(pos);       //Vincoli colore
		pos++;

		for(int i=0; i<nVincoli; i++)
		{
			x = frame.getInt(pos);
			pos++;

			y = frame.getInt(pos);
			pos++;

			colorLimitationMatrix[x][y]=(Color)frame.get(pos);
			pos++;
		}

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
	public Color getColorBond(int x, int y)
	{
		return colorLimitationMatrix[x][y];
	}

	/**
	 * Get Value bond of the card in the selected position
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return Value bond of the card in the selected position
	 */
	public Value getValueBond(int x, int y)
	{
		return valueLimitationMatrix[x][y];
	}


}