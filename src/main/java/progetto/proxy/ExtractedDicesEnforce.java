package progetto.proxy;

import progetto.model.ExtractedDicesData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class ExtractedDicesEnforce implements IEnforce
{
	private final ExtractedDicesData data;

	public ExtractedDicesEnforce(ExtractedDicesData data)
	{
		this.data = data;
	}

	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getMainBoard().getExtractedDices().setData(data);
	}
}
