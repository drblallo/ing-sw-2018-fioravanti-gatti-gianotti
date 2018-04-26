package progetto.game;


import progetto.utils.AbstractObservable;

/**
 * Dice with a color and a value
 */
public final class Dice  extends AbstractObservable<Dice> {
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
		change(this);
		value=newValue;
	}

	void setColor(Color newColor)
	{
		change(this);
		color=newColor;
	}

	void increaseValue()
	{
		switch (value)
		{
			case ONE:
				change(this);
				value=Value.TWO;
				break;
			case TWO:
				change(this);
				value=Value.THREE;
				break;
			case THREE:
				change(this);
				value=Value.FOUR;
				break;
			case FOUR:
				change(this);
				value=Value.FIVE;
				break;
			case FIVE:
				change(this);
				value=Value.SIX;
				break;
			case SIX:
				break;
		}
	}

	void decreaseValue()
	{
		switch (value)
		{
			case SIX:
				change(this);
				value=Value.FIVE;
				break;
			case FIVE:
				change(this);
				value=Value.FOUR;
				break;
			case FOUR:
				change(this);
				value=Value.THREE;
				break;
			case THREE:
				change(this);
				value=Value.TWO;
				break;
			case TWO:
				change(this);
				value=Value.ONE;
				break;
			case ONE:
				break;
		}
	}

	void flip()
	{
		change(this);
		switch (value)
		{
			case ONE:
				value=Value.SIX;
				break;
			case TWO:
				value=Value.FIVE;
				break;
			case THREE:
				value=Value.FOUR;
				break;
			case FOUR:
				value=Value.THREE;
				break;
			case FIVE:
				value=Value.TWO;
				break;
			case SIX:
				value=Value.ONE;
				break;
		}
	}

}
