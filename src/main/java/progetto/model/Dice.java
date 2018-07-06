package progetto.model;

import java.io.Serializable;

/**
 * Dice with a color and a value  (immutable)
 * @author Michele
 */
public final class Dice implements Serializable {
	private final Value value;
	private final GameColor gameColor;

	/**
	 * Constructor
	 * @param value value of the dice
	 * @param gameColor color of the dice
	 */
	Dice(Value value, GameColor gameColor)
	{
		this.value = value;
		this.gameColor = gameColor;
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
	public GameColor getGameColor()
	{
		return gameColor;
	}

	/**
	 * Set value
	 * @param newValue new value of the dice
	 * @return a new Dice with newValue as Value, same Color
	 */
	public Dice setValue(Value newValue)
	{
		return new Dice(newValue, gameColor);
	}

	/**
	 * Set color
	 * @param newGameColor new color of the dice
	 * @return a new Dice with newColor as Color, same Value
	 */
	public Dice setGameColor(GameColor newGameColor)
	{
		return new Dice(value, newGameColor);
	}

	/**
	 * Increase dice Value
	 * @return a new Dice with increased value
	 */
	public Dice increaseValue()
	{
		Dice dice = this;
		switch (value)
		{
			case ONE:
				dice = new Dice(Value.TWO, gameColor);
				break;
			case TWO:
				dice = new Dice(Value.THREE, gameColor);
				break;
			case THREE:
				dice = new Dice(Value.FOUR, gameColor);
				break;
			case FOUR:
				dice = new Dice(Value.FIVE, gameColor);
				break;
			case FIVE:
				dice = new Dice(Value.SIX, gameColor);
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
	public Dice decreaseValue()
	{
		Dice dice = this;
		switch (value)
		{
			case SIX:
				dice = new Dice(Value.FIVE, gameColor);
				break;
			case FIVE:
				dice = new Dice(Value.FOUR, gameColor);
				break;
			case FOUR:
				dice = new Dice(Value.THREE, gameColor);
				break;
			case THREE:
				dice = new Dice(Value.TWO, gameColor);
				break;
			case TWO:
				dice = new Dice(Value.ONE, gameColor);
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
	public Dice flip()
	{
		Dice dice = this;
		switch (value)
		{
			case ONE:
				dice = new Dice(Value.SIX, gameColor);
				break;
			case TWO:
				dice = new Dice(Value.FIVE, gameColor);
				break;
			case THREE:
				dice = new Dice(Value.FOUR, gameColor);
				break;
			case FOUR:
				dice = new Dice(Value.THREE, gameColor);
				break;
			case FIVE:
				dice = new Dice(Value.TWO, gameColor);
				break;
			case SIX:
				dice = new Dice(Value.ONE, gameColor);
				break;
		}
		return dice;
	}

	/**
	 *
	 * @return this dice to string
	 */
	public String toString(){

		return getValue().toString() + " " + getGameColor().toString();

	}

}
