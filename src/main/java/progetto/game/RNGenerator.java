package progetto.game;

import java.util.Random;

/**
 * Class used to generate random values (with seed), platform independent
 */

public final class RNGenerator {

	private static final int MAX_VALUE_RANDOM = 6;      //6 not included

	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;

	private Random random = new Random();

	RNGenerator(long seed)
	{
		random.setSeed(seed);
	}

	/**
	 * Set Seed of Random
	 */
	public void setSeed(long seed)
	{
		random.setSeed(seed);
	}

	/**
	 * Get a int random value (maxValue not included)
	 */
	public int getNextInt(int maxValue)
	{
		return random.nextInt(maxValue);
	}

	/**
	 * Get a random Dice from the dice bag
	 */
	public Dice extractDice(DiceBag bag)
	{
		Value value;
		int randValue = random.nextInt(MAX_VALUE_RANDOM)+1;
		switch (randValue){
			case ONE:
				value = Value.ONE;
				break;
			case TWO:
				value = Value.TWO;
				break;
			case THREE:
				value = Value.THREE;
				break;
			case FOUR:
				value = Value.FOUR;
				break;
			case FIVE:
				value = Value.FIVE;
				break;
			case SIX:
				value = Value.SIX;
				break;
			default:
				value = Value.ONE;
		}
		return new Dice(value, bag.draw(rollDice(bag)));
	}

	/**
	 * Get a random position in dice bag
	 */
	public int rollDice(DiceBag db)
	{
		return random.nextInt(db.getNumberOfDices());
	}

}
