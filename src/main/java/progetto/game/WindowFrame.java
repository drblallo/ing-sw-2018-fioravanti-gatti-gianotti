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

	public int getFavorToken()
	{
		return favorToken;
	}

	public String getName()
	{
		return name;
	}

	public Color getColorBond(int x, int y)
	{
		return colorLimitationMatrix[x][y];
	}

	public Value getValueBond(int x, int y)
	{
		return valueLimitationMatrix[x][y];
	}

}