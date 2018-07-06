package progetto.model;

/**
 * This item contains all of the player related informations
 * @author Michele
 */
public interface IPlayerBoard extends IContainer<PlayerBoardData>{

	/**
	 *
	 * @return the picked dices
	 */
	IContainer<PickedDicesSlotData> getPickedDicesSlot();

	/**
	 *
	 * @return the placed frame
	 */
	IContainer<DicePlacedFrameData> getDicePlacedFrame();
}
