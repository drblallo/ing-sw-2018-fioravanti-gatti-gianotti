package progetto.clientmodelproxy;

import progetto.game.*;

public class PlayerBoardProxy extends DataContainerProxy<PlayerBoardData> implements IPlayerBoard
{

	private DataContainerProxy<PickedDicesSlotData> pickedDicesSlotData = new DataContainerProxy<>(new PickedDicesSlotData());
	private DataContainerProxy<DicePlacedFrameData> dicePlacedFrameData = new DataContainerProxy<>(new DicePlacedFrameData());

	public PlayerBoardProxy()
	{
		super(new PlayerBoardData());
	}

	public DataContainerProxy<PickedDicesSlotData> getPickedDicesSlot() {
		return pickedDicesSlotData;
	}

	public DataContainerProxy<DicePlacedFrameData> getDicePlacedFrame() {
		return dicePlacedFrameData;
	}

}
