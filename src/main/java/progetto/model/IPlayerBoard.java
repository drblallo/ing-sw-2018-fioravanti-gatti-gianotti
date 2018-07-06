package progetto.model;

/**
 * @author Michele
 */
public interface IPlayerBoard extends IContainer<PlayerBoardData>{

	IContainer<PickedDicesSlotData> getPickedDicesSlot();
	IContainer<DicePlacedFrameData> getDicePlacedFrame();
}
