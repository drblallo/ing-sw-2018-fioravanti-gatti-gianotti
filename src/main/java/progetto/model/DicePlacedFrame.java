package progetto.model;

/**
 * Player window with positioned dice
 * @author Michele
 */
public final class DicePlacedFrame extends Container<DicePlacedFrameData> {

	/**
	 * Constructor
	 */
	DicePlacedFrame()
	{
		super(new DicePlacedFrameData());
	}

	/**
	 * Place a dice in the dice placed frame in the selected position
	 * @param newDice dice to add
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	void addDice(Dice newDice, int y, int x)
	{
		setData(getData().addDice(newDice, y, x));
	}

	/**
	 * Remove a dice from the dice placed frame, from the selected position
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return the removed dice
	 */
	public Dice removeDice(int y, int x)
	{
		Dice dice = getData().getDice(y, x);
		setData(getData().removeDice(y, x));
		return dice;
	}

}