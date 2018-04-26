package progetto.game;

import progetto.utils.AbstractObservable;

import java.util.ArrayList;

/**
 * Main gaming table
 */
public class MainBoard extends AbstractObservable<MainBoard> {

	private ArrayList<WindowFrameCouple> windowFrameCouples = new ArrayList<>();
	private ExtractedDices extractedDices = new ExtractedDices();


	void addWindowFrameCouple(WindowFrameCouple windowFrameCouple)
	{
		windowFrameCouples.add(windowFrameCouple);
		change(this);
	}

	WindowFrameCouple getWindowFrame(int index)
	{
		return windowFrameCouples.get(index);
	}

	public ExtractedDices getExtractedDices()
	{
		return extractedDices;
	}

	void addDiceExtracted(Dice dice)
	{
		extractedDices.addDice(dice);
		change(this);
	}

	public Dice getDiceExtracted(int index)
	{
		return extractedDices.getDice(index);
	}

	public int getNExtractedDices()
	{
		return extractedDices.getNumberOfDices();
	}

	void changeExtracredDice(int index, Dice dice)
	{
		extractedDices.changeDice(index, dice);
		change(this);
	}


}
