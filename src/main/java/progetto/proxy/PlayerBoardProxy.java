package progetto.proxy;

import progetto.model.*;

public class PlayerBoardProxy extends AbstractPlayerBoard
{

	private final Container<PickedDicesSlotData> pickedDicesSlotData = new Container<>(new PickedDicesSlotData());
	private final Container<DicePlacedFrameData> dicePlacedFrameData = new Container<>(new DicePlacedFrameData());

	public PlayerBoardProxy()
	{
		super(new PlayerBoardData());
	}

	public Container<PickedDicesSlotData> getPickedDicesSlot() {
		return pickedDicesSlotData;
	}

	public Container<DicePlacedFrameData> getDicePlacedFrame() {
		return dicePlacedFrameData;
	}

}
