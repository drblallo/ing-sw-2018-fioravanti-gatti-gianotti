package progetto.model;

/**
 * Enum for available colors
 */
public enum Color {
	YELLOW, RED, BLUE, GREEN, PURPLE;


	@Override
	public String toString()
	{
		String string;
		switch (this)
		{
			case YELLOW:
				string = "Giallo";
				break;
			case RED:
				string = "Rosso";
				break;
			case BLUE:
				string = "Blu";
				break;
			case GREEN:
				string = "Verde";
				break;
			default:
				string = "Viola";
		}
		return string;
	}
}