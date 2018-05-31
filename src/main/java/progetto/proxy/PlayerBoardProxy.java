package progetto.proxy;

import progetto.model.*;

public class PlayerBoardProxy extends AbstractPlayerBoard
{

	private DataContainer<PickedDicesSlotData> pickedDicesSlotData = new DataContainer<>(new PickedDicesSlotData());
	private DataContainer<DicePlacedFrameData> dicePlacedFrameData = new DataContainer<>(new DicePlacedFrameData());

	public PlayerBoardProxy()
	{
		super(new PlayerBoardData());
	}

	public DataContainer<PickedDicesSlotData> getPickedDicesSlot() {
		return pickedDicesSlotData;
	}

	public DataContainer<DicePlacedFrameData> getDicePlacedFrame() {
		return dicePlacedFrameData;
	}

}
