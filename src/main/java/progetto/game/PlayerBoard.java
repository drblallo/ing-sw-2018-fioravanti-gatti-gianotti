package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Player table
 */
public class PlayerBoard extends AbstractObservable<PlayerBoardData> {

	private PlayerBoardData playerBoardData = new PlayerBoardData();

	private final DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();
	private final PickedDicesSlot pickedDicesSlot = new PickedDicesSlot();

	public PlayerBoardData getPlayerBoardData()
	{
		return playerBoardData;
	}

	public int getNPickedDices()
	{
		return pickedDicesSlot.getNDices();
	}

	public PickedDicesSlot getPickedDicesSlot()
	{
		return pickedDicesSlot;
	}

	public DicePlacedFrame getDicePlacedFrame()
	{
		return dicePlacedFrame;
	}

	public int getNDicesPlaced()
	{
		return dicePlacedFrame.getDicePlacedFrameData().getNDices();
	}


	/**
	 * Set player Window frame
	 * @param windowFrameCouple window frame couple
	 * @param side side to take in the window frame couple
	 */
	void setWindowFrame(WindowFrameCouple windowFrameCouple, int side)
	{
		playerBoardData = playerBoardData.setWindowFrame(windowFrameCouple.getWindowFrame(side));
		change(playerBoardData);
	}

	/**
	 * Set player window frame
	 * @param windowFrame window frame to set
	 */
	void setWindowFrame(WindowFrame windowFrame)
	{
		playerBoardData = playerBoardData.setWindowFrame(windowFrame);
		change(playerBoardData);

	}

	/**
	 * Add dice in placed frame
	 * @param dice dice to add
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	void addDiceInPlacedFrame(Dice dice, int y, int x)
	{
		dicePlacedFrame.addDice(dice, y, x);
	}

	/**
	 * Get dice from placed frame
	 * @param x pos horizontal
	 * @param y pos vertical
	 * @return selected dice
	 */
	public Dice getDiceFromPlacedFrame(int y, int x)
	{
		return dicePlacedFrame.getDicePlacedFrameData().getDice(y, x);
	}

	/**
	 * Remove dice from placed frame
	 * @param x pos horizontal
	 * @param y pos vertical
	 */
	void removeDiceFromDicesPlaced(int y, int x)
	{
		dicePlacedFrame.removeDice(y, x);
	}

	/**
	 * Add dice to picked slot
	 * @param dice dice to add
	 */
	void addDiceToPickedSlot (Dice dice)
	{
		pickedDicesSlot.add(dice, false, false, false);
	}

}
