package progetto.model;

public interface IPlayerBoard extends IContainer<PlayerBoardData>{

	IContainer<PickedDicesSlotData> getPickedDicesSlot();
	IContainer<DicePlacedFrameData> getDicePlacedFrame();
}
