package progetto.game;

public class PlaceDiceAction extends AbstractGameAction {

	private int nDice;

	private int x;
	private int y;

	PlaceDiceAction(int nPlayer, int nDice, int y, int x)
	{
		super(nPlayer);
		this.nDice = nDice;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean canBeExecuted(Game game)
	{
		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getPickedDicesSlotData().getDicePlacementCondition(nDice);
		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		return game.getMainBoard().getMainBoardData().getGameState().getClass() == RoundState.class &&
				dicePlacementCondition!=null &&
				dicePlacementCondition.canBePlaced(y, x, playerBoard);

	}

	@Override
	protected void execute(Game game)
	{
		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getPickedDicesSlot().remove(nDice).getDice();
		playerBoard.addDiceInPlacedFrame(dice, y, x);

	}
}
