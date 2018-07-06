package progetto.controller;

import progetto.model.*;

/**
 * Action to end turn
 * @author Michele
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
	 * Constructor to set callerID
	 * @param callerID ID of the caller
	 */
	public EndTurnAction(int callerID)
	{
		super(callerID);
	}

	/**
	 * Verify if the action can be executed
	 * @param game the model in which this command should be executed
	 * @return true if the action can be executed
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		return (game.getMainBoard().getData().getGameState().getClass() == RoundState.class ||
				game.getMainBoard().getData().getGameState().getClass() == ToolCardState.class) &&
				getCallerID() == game.getRoundInformation().getData().getCurrentPlayer();
	}

	/**
	 * Execute the action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		PickedDicesSlot pickedDicesSlot = game.getPlayerBoard(getCallerID()).getPickedDicesSlot();
		ExtractedDices extractedDices = game.getMainBoard().getExtractedDices();

		Dice toolCardDice = game.getRoundInformation().getData().getToolCardParameters().getDice();

		if(toolCardDice != null)
		{
			game.getRoundInformation().setDice(null);
			pickedDicesSlot.add(game.getRNGenerator().rollAgain(toolCardDice));

		}

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

		RoundInformation roundInformation = game.getRoundInformation();

		roundInformation.setPickedDice(false);
		roundInformation.setUsedToolCard(false);

		roundInformation.delParamToolCard();

		int nextPlayer = roundInformation.getData().getNextPlayer();
		roundInformation.removeNextPlayer();

		if(nextPlayer == -1)
		{
			game.setState(new EndRoundState());
		}
		else
		{
			roundInformation.setCurrentPlayer(nextPlayer);
			game.setState(new RoundState());
		}

	}
}
