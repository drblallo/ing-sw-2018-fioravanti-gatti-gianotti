package progetto.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dice bag with 90 dices in 5 colors
*/
public final class DiceBag{

	private static final int NUMBER_OF_DICES_PER_COLOR = 18;

	private final ArrayList<Color> dices = new ArrayList<>();

	private static final Logger LOGGER = Logger.getLogger(DiceBag.class.getName());

	/**
	 * Constructor of the dice bag with 90 dices, 18 * 5 colors
	 */
	DiceBag()
	{
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(Color.YELLOW);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(Color.RED);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(Color.BLUE);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(Color.GREEN);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(Color.PURPLE);
		}
	}

	/**
	 * Remove the dice in position index
	 * @param index position of the color to return
	 * @return the color of the dice in position index and remove it
	 */
	Color draw(int index)
	{
		try
		{
			return dices.remove(index);
		}
		catch(IndexOutOfBoundsException e)
		{
			LOGGER.log(Level.SEVERE,"Wrong index");
			return null;
		}
	}

	/**
	 * Add a dice to the bag (with Color color)
	 * @param color
	 */
	public void add(Color color)
	{
		dices.add(color);
	}

	/**
	 * Get the number of dices in the diceBag
	 * @return the number of dices in the bag
	 */
	int getNumberOfDices()
	{
		return dices.size();
	}
}
