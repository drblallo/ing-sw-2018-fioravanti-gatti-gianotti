package progetto.controller;

import progetto.model.*;

/**
 * Action to end turn
 */
public class EndTurnAction extends AbstractExecutibleGameAction
{

	/**
	 * Constructor without parameters
	 */
	public EndTurnAction(){

		super(-1);

	}

	/**
	 * Constructor su set nPlayer
	 * @param nPlayer
	 */
	public EndTurnAction(int nPlayer)
	{
		super(nPlayer);
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		return (game.getMainBoard().getData().getGameState().getClass() == RoundState.class ||
				game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class) &&
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

		AbstractGameAction gameAction = new CancelToolCardUseAction(getCallerID());
		if(gameAction.canBeExecuted(game))
		{
			gameAction.execute(game);
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
