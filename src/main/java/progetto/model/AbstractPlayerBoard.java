package progetto.model;

/**
 * This item contains all of the player related informations
 * @author Michele
 */
public abstract class AbstractPlayerBoard extends Container<PlayerBoardData> implements IPlayerBoard
{
	/**
	 * public constructor
	 * @param d data of the associated player board
	 */
	public AbstractPlayerBoard(PlayerBoardData d)
	{
		super(d);
	}

	/**
	 *
	 * @return the picked dice slot related to this player
	 */
	public abstract Container<PickedDicesSlotData> getPickedDicesSlot();

	/**
	 *
	 * @return the dice placed frame related to this player
	 */
	public abstract Container<DicePlacedFrameData> getDicePlacedFrame();
}
