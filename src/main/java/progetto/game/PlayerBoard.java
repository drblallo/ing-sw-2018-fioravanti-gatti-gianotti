package progetto.game;

/**
 * Player table
 */
public class PlayerBoard extends AbstractPlayerBoard
{


	PlayerBoard()
	{
		super(new PlayerBoardData());
	}

	private final DicePlacedFrame dicePlacedFrame = new DicePlacedFrame();
	private final PickedDicesSlot pickedDicesSlot = new PickedDicesSlot();

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
		return dicePlacedFrame.getData().getNDices();
	}

	/**
	 * Set player Window frame
	 * @param windowFrameCouple window frame couple
	 * @param side side to take in the window frame couple
	 */
	void setWindowFrame(WindowFrameCouple windowFrameCouple, int side)
	{
		setData(getData().setWindowFrame(windowFrameCouple.getWindowFrame(side)));
	}

	/**
	 * Set player window frame
	 * @param windowFrame window frame to set
	 */
	void setWindowFrame(WindowFrame windowFrame)
	{
		setData(getData().setWindowFrame(windowFrame));
	}

	/**
	 * Set player extracted window frame couples
	 * @param extractedWindowFrame window frame to set
	 */
	void setExtractedWindowFrameCouples(WindowFrameCouple[] extractedWindowFrame)
	{
		WindowFrameCouple[] newExtractedWindowFrame = new WindowFrameCouple[extractedWindowFrame.length];
		for(int i=0; i<extractedWindowFrame.length; i++)
		{
			newExtractedWindowFrame[i] = extractedWindowFrame[i];
		}
		setData(getData().setExtractedWindowFrame(newExtractedWindowFrame));
	}

	/**
	 * Set player window frame
	 * @param couple couple to set
	 * @param side side to set
	 */
	void setWindowFrame(int couple, int side)
	{
		setData(getData().setWindowFrame(couple, side));
	}

	/**
	 * Set player window frame (empty window frame)
	 */
	void setEmptyWindowFrame()
	{
		setData(getData().setEmptyWindowFrame());
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
		return dicePlacedFrame.getData().getDice(y, x);
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

	void addPrivateObjectiveCard(AbstractPrivateObjectiveCard privateObjectiveCard)
	{
		setData(getData().addPrivateObjectiveCard(privateObjectiveCard));
	}
}
