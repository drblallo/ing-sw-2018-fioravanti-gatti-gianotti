package progetto.game;

public abstract class AbstractMainBoard extends DataContainer<MainBoardData>
{

	public AbstractMainBoard(MainBoardData d)
	{
		super(d);
	}
	/**
	 * Get extracted dices
	 * @return extractedDices
	 */
	public abstract DataContainer<ExtractedDicesData> getExtractedDices();
}
