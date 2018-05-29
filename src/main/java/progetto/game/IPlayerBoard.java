package progetto.game;

public interface IPlayerBoard extends IDataContainer<PlayerBoardData>
{
	IDataContainer<PickedDicesSlotData> getPickedDicesSlot();
	IDataContainer<DicePlacedFrameData> getDicePlacedFrame();
}
