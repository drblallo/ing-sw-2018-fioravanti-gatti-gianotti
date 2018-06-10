package progetto.controller;

import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

public class ToolCardSetPickedDiceAction extends AbstractExecutibleGameAction{

	private final int nDice;

	private static final int CARD1 = 1;
	private static final int CARD5 = 5;
	private static final int CARD6 = 6;
	private static final int CARD9 = 9;
	private static final int CARD10 = 10;
	private static final int CARD11 = 11;


	public ToolCardSetPickedDiceAction()
	{
		super();
		nDice = -1;

	}

	public ToolCardSetPickedDiceAction(int nPlayer, int nDice)
	{
		super(nPlayer);
		this.nDice = nDice;

	}

	@Override
	public boolean canBeExecuted(Model game) {
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState toolCardState = (ToolCardState) data.getGameState();

		int index = toolCardState.getIndex();

		return getCallerID()==game.getMainBoard().getData().getCurrentPlayer() &&
				data.getGameState().getClass() == ToolCardState.class &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getNDices() > nDice &&
				nDice >= 0 && verifyCards(index);
	}

	@Override
	public void execute(Model game) {
		game.getMainBoard().setParamToolCard("nDice", nDice);
	}


	private boolean verifyCards(int index)
	{
		return (index == CARD1 || index == CARD5 || index == CARD6 || index == CARD9 || index == CARD10 || index == CARD11);
	}

}
