package progetto.model;

public abstract class AbstractMainBoard extends Container<MainBoardData>
{

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
