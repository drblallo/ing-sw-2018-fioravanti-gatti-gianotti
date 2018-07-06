package progetto.network.proxy;

import progetto.model.PickedDicesSlotData;
import progetto.network.ClientConnection;
import progetto.network.IEnforce;

/**
 * @author Massimo
 * enforce used to set the state of the dice slot on the client
 */
public class PickedDicesSlotEnforce implements IEnforce
{
	private final int target;
	private final PickedDicesSlotData data;

	/**
	 * @param data the data to be sent to the player
	 * @param target the player chair that will receive this item
	 */
	public PickedDicesSlotEnforce(PickedDicesSlotData data, int target)
	{
		this.data = data;
		this.target = target;
	}

	/**
	 *
	 * @param c the client connection that received this enforce
	 */
	@Override
	public void execute(ClientConnection c) {
		c.getProxy().getPlayerBoard(target).getPickedDicesSlot().setData(data);
	}
}
