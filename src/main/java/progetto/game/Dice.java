package progetto.game;

/**
 * Dice with a color and a value
 */
public final class Dice {
	private Value value;
	private Color color;

	Dice(Value value, Color color)
	{
		this.value=value;
		this.color=color;
	}

	Value getValue()
	{
		return value;
	}

	Color getColor()
	{
		return color;
	}

	void setValue(Value newValue)
	{
		value=newValue;
	}

	void setColor(Color newColor)
	{
		color=newColor;
	}

}
