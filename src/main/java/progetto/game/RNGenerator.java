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

	private Random random = new Random();
	private long seed;

	/**
	 * Constructor
	 * @param seed of random generator
	 */
	RNGenerator(long seed)
	{
		random.setSeed(seed);
		this.seed=seed;
	}

	/**
	 * Get seed
	 * @return seed
	 */
	long getSeed()
	{
		return seed;
	}

	/**
	 * Set Seed
	 * @param seed seed to set
	 */
	public void setSeed(long seed)
	{
		this.seed = seed;
		random.setSeed(seed);
	}

	/**
	 * Get a int random value
	 * @param maxValue max value (not included)
	 * @return random value
	 */
	public int getNextInt(int maxValue)
	{
		return random.nextInt(maxValue);
	}

	/**
	 * Get a random Dice from the dice bag
	 * @param bag diceBag from which draw the dice
	 */
	public Dice extractDice(DiceBag bag)
	{
		Value value = extractValue();
		return new Dice(value, bag.draw(rollDice(bag)));
	}

	/**
	 * Get a random position in dice bag
	 * @param db diceBag from which draw the dice
	 * @return random position
	 */
	public int rollDice(DiceBag db)
	{
		return random.nextInt(db.getNumberOfDices());
	}

	/**
	 * Roll again a dice
	 * @param dice dice to roll again
	 * @return rolled dice
	 */
	public Dice rollAgain(Dice dice)
	{
		Value value = extractValue();
		return new Dice(value, dice.getColor());
	}

	/**
	 * Get random Value for the dice
	 * @return random Value (ONE to SIX)
	 */
	private Value extractValue ()
	{
		int randValue = random.nextInt(MAX_VALUE_RANDOM)+1;
		Value value;
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
			default:
				value = Value.SIX;
				break;
		}
		return value;
	}

}
