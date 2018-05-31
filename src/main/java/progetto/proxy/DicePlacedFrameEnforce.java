package progetto.proxy;

import progetto.model.DicePlacedFrameData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

public class DicePlacedFrameEnforce implements IEnforce
{

	public DicePlacedFrameEnforce(DicePlacedFrameData data, int target)
	{
		this.data = data;
		this.target = target;
	}

	private final DicePlacedFrameData data;
	private final int target;

	@Override
	public void execute(ClientConnection c)
	{
		c.getProxy().getPlayerBoard(target).getDicePlacedFrame().setData(data);
	}
}
