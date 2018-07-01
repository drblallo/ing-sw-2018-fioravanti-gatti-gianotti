package progetto.proxy;

import progetto.model.AbstractMainBoard;
import progetto.model.Container;
import progetto.model.ExtractedDicesData;
import progetto.model.MainBoardData;

public class MainBoardProxy extends AbstractMainBoard
{

	public MainBoardProxy()
	{
		super(new MainBoardData());
	}

	private final Container<ExtractedDicesData> extractedDices = new Container<>(new ExtractedDicesData());
	public final Container<ExtractedDicesData> getExtractedDices() {
		return extractedDices;
	}

}
