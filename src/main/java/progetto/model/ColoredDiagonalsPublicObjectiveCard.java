package progetto.model;

import java.util.ArrayList;

/**
 * Class for public objective card "Diagonali Colorate"
 */
public class ColoredDiagonalsPublicObjectiveCard extends AbstractPublicObjectiveCard {

	private ArrayList<Dice> dices;

	/**
	 * Constructor
	 */
	ColoredDiagonalsPublicObjectiveCard() {
		super("Diagonali Colorate", "Numero di dadi dello stesso colore diagonalmente adiacenti");
	}

	/**
	 * Evaluate frame
	 * @param dicePlacedFrame dice placed frame
	 * @return n point
	 */
	@Override
	public int evaluateFrame(DicePlacedFrame dicePlacedFrame) {

		DicePlacedFrameData dicePlacedFrameData = dicePlacedFrame.getData();

		dices = new ArrayList<>();

		for(int i=0; i<MAX_NUMBER_OF_ROWS-1; i++)
		{
			for(int j=0; j<MAX_NUMBER_OF_COLUMNS-1; j++)
			{
				checkDices(dicePlacedFrameData.getDice(i, j), dicePlacedFrameData.getDice(i+1, j+1));
				checkDices(dicePlacedFrameData.getDice(i, j+1), dicePlacedFrameData.getDice(i+1, j));
			}
		}

		return dices.size();

	}

	/**
	 *Support class to verify two dices
	 * @param dice1
	 * @param dice2
	 */
	private void checkDices(Dice dice1, Dice dice2)
	{
		if(dice1!=null && dice2!=null && dice1.getGameColor()==dice2.getGameColor())
		{
			if(!dices.contains(dice1))
			{
				dices.add(dice1);
			}
			if(!dices.contains(dice2))
			{
				dices.add(dice2);
			}
		}
	}

}
