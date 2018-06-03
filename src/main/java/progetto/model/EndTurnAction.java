package progetto.model;

public class EndTurnAction extends AbstractGameAction
{

	public EndTurnAction(){

		super(-1);

	}

	public EndTurnAction(int nPlayer)
	{
		super(nPlayer);
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		return game.getMainBoard().getData().getGameState().getClass() == RoundState.class &&
				getCallerID() == game.getMainBoard().getData().getCurrentPlayer();
	}

	@Override
	public void execute(Model game)
	{
		PickedDicesSlot pickedDicesSlot = game.getPlayerBoard(getCallerID()).getPickedDicesSlot();
		ExtractedDices extractedDices = game.getMainBoard().getExtractedDices();

		while(pickedDicesSlot.getData().getNDices()>0)
		{
			Dice dice = pickedDicesSlot.remove(0).getDice();
			extractedDices.addDice(dice);
		}

		game.getMainBoard().delParamToolCard();

		MainBoard mainBoard = game.getMainBoard();

		int nextPlayer = mainBoard.getNextPlayer();

		if(nextPlayer == -1)
		{
			game.setState(new EndRoundState());
		}
		else
		{
			mainBoard.setCurrentPlayer(nextPlayer);
			game.setState(new RoundState());
		}

	}
}
