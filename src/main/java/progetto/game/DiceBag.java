package progetto.game;

import progetto.utils.AbstractObservable;

import java.util.ArrayList;

/**
 * Dice bag with 90 dices in 5 colors
 */
public final class DiceBag extends AbstractObservable<DiceBag> {

	private static final int NUMBER_OF_DICES_PER_COLOR = 18;

	private final ArrayList<Color> dices = new ArrayList<>();

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
	 * Return the color of the dice in position index and remove it
	 */
	Color draw(int index)
	{
		change(this);
		return dices.remove(index);
	}

	/**
	 * Add a dice to the bag
	 */
	void add(Color color)
	{
		change(this);
		dices.add(color);
	}

	/**
	 * Return the number of dices in the bag
	 */
	int getNumberOfDices()
	{
		return dices.size();
	}
}
