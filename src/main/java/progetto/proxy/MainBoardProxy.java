package progetto.proxy;

import progetto.model.AbstractMainBoard;
import progetto.model.ExtractedDicesData;
import progetto.model.MainBoardData;

public class MainBoardProxy extends AbstractMainBoard
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
