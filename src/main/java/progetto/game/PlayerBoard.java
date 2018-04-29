package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * Player playing table
 */
public class PlayerBoard extends AbstractObservable<PlayerBoard> {

	private WindowFrame windowFrame;
	private final DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();
	private final PickedDicesSlot pickedDicesSlot = new PickedDicesSlot();

	void setWindowFrame(WindowFrameCouple windowFrameCouple, int side)
	{
		change(this);
		this.windowFrame = windowFrameCouple.getWindowFrame(side);
	}

	void setWindowFrame(WindowFrame windowFrame)
	{
		change(this);
		this.windowFrame = windowFrame;
	}

	void addDiceInPlacedFrame(Dice dice, int x, int y)
	{
		dicePlacedFrame.addDice(dice, x, y);
		change(this);
	}

	public Dice getDiceFromPlacedFrame(int x, int y)
	{
		return dicePlacedFrame.getDice(x, y);
	}

	public int getNDicesPlaced()
	{
		return dicePlacedFrame.getNDices();
	}

	void removeDiceFromDicesPlaced(int x, int y)
	{
		dicePlacedFrame.removeDice(x, y);
		change(this);
	}

	public DicePlacedFrame getDicePlacedFrame()
	{
		return dicePlacedFrame;
	}

	public PickedDicesSlot getPickedDicesSlot()
	{
		return pickedDicesSlot;
	}

	void addDiceToPickedSlot (Dice dice)
	{
		pickedDicesSlot.add(dice, false, false, false);
		change(this);
	}

	public WindowFrame getWindowFrame()
	{
		return windowFrame;
	}

	public int getNPickedDices()
	{
		return pickedDicesSlot.getNDices();
	}

}
