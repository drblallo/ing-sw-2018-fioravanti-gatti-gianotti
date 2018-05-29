package progetto.clientmodelproxy;

import progetto.game.*;

public class MainBoardProxy extends DataContainerProxy<MainBoardData> implements IMainBoard
{

	public MainBoardProxy()
	{
		super(new MainBoardData());
	}

	private DataContainerProxy<ExtractedDicesData> extractedDices = new DataContainerProxy<>(new ExtractedDicesData());


	public DataContainerProxy<ExtractedDicesData> getExtractedDices() {
		return extractedDices;
	}

}
