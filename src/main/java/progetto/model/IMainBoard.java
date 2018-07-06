package progetto.model;

/**
 * @author Michele
 */
public interface IMainBoard extends IContainer<MainBoardData>{
	IContainer<ExtractedDicesData> getExtractedDices();
}
