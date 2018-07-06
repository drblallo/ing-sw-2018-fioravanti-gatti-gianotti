package progetto.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dice bag with 90 dices in 5 colors
 * @author Michele
 */
public final class DiceBag{

	private static final int NUMBER_OF_DICES_PER_COLOR = 18;

	private final ArrayList<GameColor> dices = new ArrayList<>();

	private static final Logger LOGGER = Logger.getLogger(DiceBag.class.getName());

	/**
	 * Constructor of the dice bag with 90 dices, 18 * 5 colors
	 */
	DiceBag()
	{
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(GameColor.YELLOW);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(GameColor.RED);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(GameColor.BLUE);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(GameColor.GREEN);
		}
		for(int i=0; i<NUMBER_OF_DICES_PER_COLOR; i++)
		{
			dices.add(GameColor.PURPLE);
		}
	}

	/**
	 * Remove the dice in position index
	 * @param index position of the color to return
	 * @return the color of the dice in position index and remove it
	 */
	GameColor draw(int index)
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
	 * @param gameColor
	 */
	public void add(GameColor gameColor)
	{
		dices.add(gameColor);
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
