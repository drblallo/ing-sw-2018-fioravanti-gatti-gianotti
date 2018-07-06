package progetto.model;

/**
 * Class that contains most of the object that have a lifetime equals to the one of the game and not
 * belong to any player
 * @author Michele
 */
public interface IMainBoard extends IContainer<MainBoardData>{
	/**
	 *
	 * @return the extracted dices
	 */
	IContainer<ExtractedDicesData> getExtractedDices();
}
