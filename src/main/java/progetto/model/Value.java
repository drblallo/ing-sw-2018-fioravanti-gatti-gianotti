package progetto.model;

/**
 * Enum for available values
 */
public enum Value {
	ONE, TWO, THREE, FOUR, FIVE, SIX;

	@Override
	public String toString()
	{
		String string;
		switch (this)
		{
			case ONE:
				string = "1";
				break;
			case TWO:
				string = "2";
				break;
			case THREE:
				string = "3";
				break;
			case FOUR:
				string = "4";
				break;
			case FIVE:
				string = "5";
				break;
			default:
				string = "6";
		}
		return string;
	}

}
