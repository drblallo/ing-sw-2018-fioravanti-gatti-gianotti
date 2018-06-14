package progetto.model;

public interface IMainBoard extends IContainer<MainBoardData>{
	IContainer<ExtractedDicesData> getExtractedDices();
}
