package progetto.game;

public class PickDiceAction extends AbstractGameAction {

	private final int nDice;

	public PickDiceAction(){

		super(-1);
		nDice = -1;

	};

	public PickDiceAction(int nPlayer, int nDice)
	{
		super(nPlayer);
		this.nDice = nDice;
	}

	@Override
	public boolean canBeExecuted(Game game) {

		return game.getMainBoard().getMainBoardData().getGameState().getClass() == RoundState.class &&
				getCallerID() == game.getMainBoard().getMainBoardData().getCurrentPlayer() &&
				game.getMainBoard().getExtractedDices().getExtractedDicesData().getDice(nDice) != null;

	}

	@Override
	protected void execute(Game game)
	{
		Dice dice = game.getMainBoard().getExtractedDices().removeDice(nDice);
		game.getPlayerBoard(getCallerID()).getPickedDicesSlot().add(dice, false, false, false);
	}
}
