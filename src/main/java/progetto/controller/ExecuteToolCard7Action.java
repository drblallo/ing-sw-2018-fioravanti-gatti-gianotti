package progetto.controller;

import progetto.model.*;

import java.util.List;

/**
 * Action to execute tool card 7
 */
public class ExecuteToolCard7Action extends AbstractExecutibleGameAction{

	private static final int INDEX = 7;

	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCard7Action()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCard7Action(int nPlayer)
	{
		super(nPlayer);
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(Model game)
	{
		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		if(currentPlayer != getCallerID() ||
				game.getMainBoard().getData().getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState cardState = (ToolCardState)game.getMainBoard().getData().getGameState();



		List<Integer> playerQueue = game.getMainBoard().getRoundPlayerList();
		int nPlayer = getCallerID();

		while(!playerQueue.isEmpty())
		{
			int nextP = playerQueue.remove(0);
			if (nextP == nPlayer)   //check second turn of the player
			{
				return false;
			}
		}

		return cardState.getIndex() == INDEX &&
				game.getPlayerBoard(getCallerID()).getPickedDicesSlot().getNDices() == 0 ;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		ExtractedDices extractedDices = game.getMainBoard().getExtractedDices();
		Dice dice;

		for(int i=0; i<extractedDices.getData().getNumberOfDices(); i++)
		{
			dice = extractedDices.getData().getDice(i);
			extractedDices.changeDice(i, game.getRNGenerator().rollAgain(dice));
		}

		game.getMainBoard().delParamToolCard();
		game.setState(new RoundState());

	}

}
