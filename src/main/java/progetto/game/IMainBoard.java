package progetto.game;

public interface IMainBoard extends IDataContainer<MainBoardData>
{
	/**
	 * Get extracted dices
	 * @return extractedDices
	 */
	IDataContainer<ExtractedDicesData> getExtractedDices();
}
