package progetto.game;

/**
 * Support class to find nearby locations in the window
 */
public class Couple {
	private int dx;
	private int dy;

	Couple(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	int getDx()
	{
		return dx;
	}

	int getDy()
	{
		return dy;
	}
}
