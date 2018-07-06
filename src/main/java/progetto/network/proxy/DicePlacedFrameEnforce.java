package progetto.network.proxy;

import progetto.model.DicePlacedFrameData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * the enforce that is used to update the dicePlaced data used by the client
 */
public class DicePlacedFrameEnforce implements IEnforce
{

	/**
	 * @param data the data that will replaced those on the player
	 * @param target the id of the player that will receive the update
	 */
	public DicePlacedFrameEnforce(DicePlacedFrameData data, int target)
	{
		this.data = data;
		this.target = target;
	}

	private final DicePlacedFrameData data;
	private final int target;

	/**
	 * replace the data on the client
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c)
	{
		c.getProxy().getPlayerBoard(target).getDicePlacedFrame().setData(data);
	}
}
