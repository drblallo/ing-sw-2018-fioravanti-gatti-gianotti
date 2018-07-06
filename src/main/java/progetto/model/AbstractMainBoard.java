package progetto.model;

/**
 * Class that contains most of the object that have a lifetime equals to the one of the game and not
 * belong to any player
 * @author Michele
 */
public abstract class AbstractMainBoard extends Container<MainBoardData> implements IMainBoard
{

	/**
	 * public constructor
	 * @param d
	 */
	public AbstractMainBoard(MainBoardData d)
	{
		super(d);
	}

	/**
	 * Get extracted dices
	 * @return extractedDices
	 */
	public abstract Container<ExtractedDicesData> getExtractedDices();
}
