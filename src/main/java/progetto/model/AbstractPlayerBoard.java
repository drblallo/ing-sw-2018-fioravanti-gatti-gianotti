package progetto.model;

public abstract class AbstractPlayerBoard extends Container<PlayerBoardData> implements IPlayerBoard
{
	public AbstractPlayerBoard(PlayerBoardData d)
	{
		super(d);
	}

	public abstract Container<PickedDicesSlotData> getPickedDicesSlot();
	public abstract Container<DicePlacedFrameData> getDicePlacedFrame();
}
