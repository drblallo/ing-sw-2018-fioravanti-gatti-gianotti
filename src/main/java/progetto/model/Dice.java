package progetto.model;

import java.io.Serializable;

/**
 * Dice with a color and a value  (immutable)
 */
public final class Dice implements Serializable {
	private final Value value;
	private final Color color;

	/**
	 * Constructor
	 * @param value
	 * @param color
	 */
	Dice(Value value, Color color)
	{
		this.value = value;
		this.color = color;
	}

	/**
	 * Get value
	 * @return value
	 */
	public Value getValue()
	{
		return value;
	}

	/**
	 * Get color
	 * @return color
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * Set value
	 * @param newValue
	 * @return a new Dice with newValue as Value, same Color
	 */
	Dice setValue(Value newValue)
	{
		return new Dice(newValue, color);
	}

	/**
	 * Set color
	 * @param newColor
	 * @return a new Dice with newColor as Color, same Value
	 */
	Dice setColor(Color newColor)
	{
		return new Dice(value, newColor);
	}

	/**
	 * Increase dice Value
	 * @return a new Dice with increased value
	 */
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

	/**
	 * Decrease dice Value
	 * @return a new Dice with decreased value
	 */
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

	/**
	 * Flip dice
	 * @return a new Dice with the value on the opposite side
	 */
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

	public String toString(){

		return getValue().toString() + " " + getColor().toString();

	}

}
