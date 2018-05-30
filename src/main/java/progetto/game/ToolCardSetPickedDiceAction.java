package progetto.game;

public class ToolCardSetPickedDiceAction extends AbstractGameAction {

	private int nDice;

	public ToolCardSetPickedDiceAction(int nDice)
	{
		this.nDice = nDice;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		return game.getMainBoard().getData().getGameState().getClass() == RoughingForcepsState.class &&
				game.getPlayerBoard(game.getMainBoard().getData().getCurrentPlayer()).getPickedDicesSlot().getNDices()>nDice;
	}

	@Override
	protected void execute(Game game) {
		game.getMainBoard().setParamToolCard("nDice", nDice);
	}
}
