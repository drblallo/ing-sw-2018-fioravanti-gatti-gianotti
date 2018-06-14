package progetto.controller;

import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.model.Model;
import progetto.model.ToolCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Action to execute a tool card
 */
public class ExecuteToolCardAction extends AbstractExecutibleGameAction{

	private ArrayList<AbstractExecutibleGameAction> gameActionList = new ArrayList();

	private static final String N_CARD = "nCard";
	private static final String TOKEN = "token";
	private static final String SPDICE = "SPDice";

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
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		if(!map.containsKey(N_CARD))
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

		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nCard = map.get(N_CARD);

		if(game.getMainBoard().getData().getPlayerCount() == 1)
		{
			int nDiceToRemove = map.get(SPDICE);
			game.getMainBoard().getExtractedDices().removeDice(nDiceToRemove);
			game.getMainBoard().removeToolCard(nCard);
		}
		else
		{
			int tokenToRemove = map.get(TOKEN);
			int playerToken = game.getPlayerBoard(getCallerID()).getData().getToken();

			game.getPlayerBoard(getCallerID()).setToken(playerToken-tokenToRemove);

			game.getMainBoard().incNCallToolCard(nCard);
		}

		gameAction.execute(game);

	}

	/**
	 * Support method to set gameAction
	 * @param game
	 * @return gameAction
	 */
	private AbstractGameAction setGameAction(IModel game)
	{
		List<ToolCard> cardList = game.getMainBoard().getData().getToolCards();
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int index = cardList.get(map.get(N_CARD)).getIndex();

		return gameActionList.get(index-1);

	}

}
