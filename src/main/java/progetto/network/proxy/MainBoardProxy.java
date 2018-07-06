package progetto.network.proxy;

import progetto.model.AbstractMainBoard;
import progetto.model.Container;
import progetto.model.ExtractedDicesData;
import progetto.model.MainBoardData;

/**
 * @author Massimo
 * Proxy of main board, only contains the data but no operations
 */
public class MainBoardProxy extends AbstractMainBoard
{

	/**
	 * creates a new main board with no item in it
	 */
	public MainBoardProxy()
	{
		super(new MainBoardData());
	}

	private final Container<ExtractedDicesData> extractedDices = new Container<>(new ExtractedDicesData());
	/**
	 * the extracted dices holded in this object
	 */
	public final Container<ExtractedDicesData> getExtractedDices() {
		return extractedDices;
	}

}
