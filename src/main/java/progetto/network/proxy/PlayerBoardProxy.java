package progetto.network.proxy;

import progetto.model.*;

/**
 * @author Massimo
 * the proxy of the player board, contains all the data but does not contain any operation
 */
public class PlayerBoardProxy extends AbstractPlayerBoard
{

	private final Container<PickedDicesSlotData> pickedDicesSlotData = new Container<>(new PickedDicesSlotData());
	private final Container<DicePlacedFrameData> dicePlacedFrameData = new Container<>(new DicePlacedFrameData());

	public PlayerBoardProxy()
	{
		super(new PlayerBoardData());
	}

	/**
	 *
	 * @return the picked dice slot data holded by this object
	 */
	public Container<PickedDicesSlotData> getPickedDicesSlot() {
		return pickedDicesSlotData;
	}

	/**
	 *
	 * @return the dice placed frame data holded by this object
	 */
	public Container<DicePlacedFrameData> getDicePlacedFrame() {
		return dicePlacedFrameData;
	}

}
