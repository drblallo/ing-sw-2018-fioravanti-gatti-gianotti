package progetto.controller;

import progetto.model.Game;
import progetto.model.MainBoardData;
import progetto.model.RoughingForcepsState;

public class ToolCardSetPickedDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	public ToolCardSetPickedDiceAction()
	{
		super(-1);
		nDice = -1;

	}

	public ToolCardSetPickedDiceAction(int nDice)
	{
		this.nDice = nDice;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		MainBoardData data = game.getMainBoard().getData();

		return data.getGameState().getClass() == RoughingForcepsState.class &&
				game.getPlayerBoard(data.getCurrentPlayer()).getPickedDicesSlot().getNDices() > nDice &&
				nDice >= 0;
	}

	@Override
	public void execute(Game game) {
		game.getMainBoard().setParamToolCard("nDice", nDice);
	}
}
