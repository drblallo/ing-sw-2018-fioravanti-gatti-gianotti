package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Player playing table
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


	void setWindowFrame(WindowFrameCouple windowFrameCouple, int side)
	{
		playerBoardData = playerBoardData.setWindowFrame(windowFrameCouple.getWindowFrame(side));
		change(playerBoardData);
	}

	void setWindowFrame(WindowFrame windowFrame)
	{
		playerBoardData = playerBoardData.setWindowFrame(windowFrame);
		change(playerBoardData);

	}

	void addDiceInPlacedFrame(Dice dice, int x, int y)
	{
		dicePlacedFrame.addDice(dice, x, y);
	}

	public Dice getDiceFromPlacedFrame(int x, int y)
	{
		return dicePlacedFrame.getDicePlacedFrameData().getDice(x, y);
	}

	void removeDiceFromDicesPlaced(int x, int y)
	{
		dicePlacedFrame.removeDice(x, y);
	}

	void addDiceToPickedSlot (Dice dice)
	{
		pickedDicesSlot.add(dice, false, false, false);
	}

}
