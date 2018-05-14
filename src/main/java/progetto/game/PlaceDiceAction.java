package progetto.game;

public class PlaceDiceAction extends AbstractGameAction {

	private int nDice;

	private int x;
	private int y;

	PlaceDiceAction(){

		super(-1);
		nDice = -1;
		x = -1;
		y = -1;

	}

	PlaceDiceAction(int nPlayer, int nDice, int x, int y)
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
				dicePlacementCondition.canBePlaced(x, y, playerBoard);

	}

	@Override
	protected void execute(Game game)
	{
		PlayerBoard playerBoard = game.getPlayerBoard(getCallerID());

		Dice dice = playerBoard.getPickedDicesSlot().remove(nDice).getDice();
		playerBoard.addDiceInPlacedFrame(dice, x, y);

	}
}
