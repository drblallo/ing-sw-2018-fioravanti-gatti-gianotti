package progetto.controller;

import progetto.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Action to execute a tool card
 */
public class ExecuteToolCardAction extends AbstractExecutibleGameAction{

	private ArrayList<AbstractExecutibleGameAction> gameActionList = new ArrayList();


	/**
	 * Constructor without parameters
	 */
	public ExecuteToolCardAction()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param nPlayer
	 */
	public ExecuteToolCardAction(int nPlayer)
	{
		super(nPlayer);
		gameActionList.add(new ExecuteToolCard1Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard2Or3Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard2Or3Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard4Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard5Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard6Or10Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard7Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard8Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard9Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard6Or10Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard11Action(getCallerID()));
		gameActionList.add(new ExecuteToolCard12Action(getCallerID()));
	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game)
	{
		RoundInformationData roundInformationData = game.getRoundInformation().getData();

		if(roundInformationData.getToolCardParameters().getNCard() == -1)
		{
			return false;
		}

		AbstractGameAction gameAction = setGameAction(game);

		if(gameAction == null)
		{
			return false;
		}

		return gameAction.canBeExecuted(game);

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game)
	{
		AbstractGameAction gameAction = setGameAction(game);


		RoundInformation roundInformation = game.getRoundInformation();
		int nCard = roundInformation.getData().getToolCardParameters().getNCard();

		if(game.getMainBoard().getData().getPlayerCount() == 1)
		{
			int nDiceToRemove = roundInformation.getData().getToolCardParameters().getSPDice();
			game.getMainBoard().getExtractedDices().removeDice(nDiceToRemove);
			game.getMainBoard().removeToolCard(nCard);
		}
		else
		{
			int tokenToRemove = roundInformation.getData().getToolCardParameters().getToken();
			int playerToken = game.getPlayerBoard(getCallerID()).getData().getToken();

			game.getPlayerBoard(getCallerID()).setToken(playerToken-tokenToRemove);

			game.getMainBoard().incNCallToolCard(nCard);
		}

		game.getRoundInformation().setUsedToolCard(true);

		gameAction.execute(game);

		game.setState(new RoundState());

	}

	/**
	 * Support method to set gameAction
	 * @param game
	 * @return gameAction
	 */
	private AbstractGameAction setGameAction(IModel game)
	{
		List<ToolCard> cardList = game.getMainBoard().getData().getToolCards();
		RoundInformationData roundInformationData = game.getRoundInformation().getData();
		int nCard = roundInformationData.getToolCardParameters().getNCard();
		int index = cardList.get(nCard).getIndex();

		return gameActionList.get(index-1);

	}

}
