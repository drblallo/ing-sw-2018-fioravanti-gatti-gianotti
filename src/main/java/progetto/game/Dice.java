package progetto.game;

/**
 * Dice with a color and a value
 */
public final class Dice {
	private final Value value;
	private final Color color;

	Dice(Value value, Color color)
	{
		this.value = value;
		this.color = color;
	}

	public Value getValue()
	{
		return value;
	}

	public Color getColor()
	{
		return color;
	}

	Dice setValue(Value newValue)
	{
		return new Dice(newValue, color);
	}

	Dice setColor(Color newColor)
	{
		return new Dice(value, newColor);
	}

	Dice increaseValue()
	{
		Dice dice = this;
		switch (value)
		{
			case ONE:
				dice = new Dice(Value.TWO, color);
				break;
			case TWO:
				dice = new Dice(Value.THREE, color);
				break;
			case THREE:
				dice = new Dice(Value.FOUR, color);
				break;
			case FOUR:
				dice = new Dice(Value.FIVE, color);
				break;
			case FIVE:
				dice = new Dice(Value.SIX, color);
				break;
			case SIX:
				break;
		}
		return dice;
	}

	Dice decreaseValue()
	{
		Dice dice = this;
		switch (value)
		{
			case SIX:
				dice = new Dice(Value.FIVE, color);
				break;
			case FIVE:
				dice = new Dice(Value.FOUR, color);
				break;
			case FOUR:
				dice = new Dice(Value.THREE, color);
				break;
			case THREE:
				dice = new Dice(Value.TWO, color);
				break;
			case TWO:
				dice = new Dice(Value.ONE, color);
				break;
			case ONE:
				break;
		}
		return dice;
	}

	Dice flip()
	{
		Dice dice = this;
		switch (value)
		{
			case ONE:
				dice = new Dice(Value.SIX, color);
				break;
			case TWO:
				dice = new Dice(Value.FIVE, color);
				break;
			case THREE:
				dice = new Dice(Value.FOUR, color);
				break;
			case FOUR:
				dice = new Dice(Value.THREE, color);
				break;
			case FIVE:
				dice = new Dice(Value.TWO, color);
				break;
			case SIX:
				dice = new Dice(Value.ONE, color);
				break;
		}
		return dice;
	}

}
