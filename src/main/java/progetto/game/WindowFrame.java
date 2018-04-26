package progetto.game;

import org.json.JSONArray;
import progetto.utils.AbstractObservable;

import java.io.Serializable;

/**
 * Window frame for Window Pattern Card. Upload by file using JSON - JSONArray.
 */
public final class WindowFrame extends AbstractObservable<WindowFrame> implements Serializable {

	private static final int MAX_NUMBER_OF_ROWS = 4;
	private static final int MAX_NUMBER_OF_COLUMNS = 5;

	private Value[][] valueLimitationMatrix = new Value[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];
	private Color[][] colorLimitationMatrix = new Color[MAX_NUMBER_OF_COLUMNS][MAX_NUMBER_OF_ROWS];
	private int favorToken;
	private String name;

	WindowFrame(JSONArray frame)
	{
		int pos=0;

		int nVincoli;
		int x;
		int y;

		name = frame.getString(pos);
		pos++;

		setFavorToken(frame.getInt(pos));
		pos++;

		nVincoli = frame.getInt(pos);       //Vincoli valore
		pos++;

		for(int i=0; i<nVincoli; i++)
		{
			x = frame.getInt(pos);
			pos++;

			y = frame.getInt(pos);
			pos++;

			addValueBond(x, y, (Value)frame.get(pos));
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

			addColorBond(x, y, (Color)frame.get(pos));
			pos++;
		}

	}

	void setFavorToken(int favorToken)
	{
		change(this);
		this.favorToken=favorToken;
	}

	void addValueBond(int x, int y, Value value)
	{
		change(this);
		valueLimitationMatrix[x][y]=value;
	}

	void addColorBond(int x, int y, Color color)
	{
		change(this);
		colorLimitationMatrix[x][y]=color;
	}

	public int getFavorToken()
	{
		return favorToken;
	}

	public Color getColorBond(int x, int y)
	{
		return colorLimitationMatrix[x][y];
	}

	public Value getValueBond(int x, int y)
	{
		return valueLimitationMatrix[x][y];
	}

	public String getName()
	{
		return name;
	}

}