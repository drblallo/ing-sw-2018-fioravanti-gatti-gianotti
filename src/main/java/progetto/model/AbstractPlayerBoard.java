package progetto.model;

public abstract class AbstractPlayerBoard extends DataContainer<PlayerBoardData>
{
	public AbstractPlayerBoard(PlayerBoardData d)
	{
		super(d);
	}

	public abstract DataContainer<PickedDicesSlotData> getPickedDicesSlot();
	public abstract DataContainer<DicePlacedFrameData> getDicePlacedFrame();
}
