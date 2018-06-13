package progetto.controller;

import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

import java.util.Map;

/**
 * Action to select a dice from placed dice frame
 */
public class ToolCardSetPlacedDiceAction extends AbstractExecutibleGameAction{

	private final int x;
	private final int y;

	private static final String XPOS = "XPlacedDice";
	private static final String YPOS = "YPlacedDice";
	private static final String XPOS2 = "XPlacedDice2";
	private static final String YPOS2 = "YPlacedDice2";

	private static final int CARD2 = 2;
	private static final int CARD3 = 3;
	private static final int CARD4 = 4;
	private static final int CARD12 = 12;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetPlacedDiceAction()
	{
		super();
		x = -1;
		y = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param y y - pos in dice placed frame
	 * @param x x - pos in dice placed frame
	 */
	public ToolCardSetPlacedDiceAction(int nPlayer, int y, int x)
	{
		super(nPlayer);
		this.x = x;
		this.y = y;

	}

	/**
	 * Verify if action can be executed
	 * @param game the model in which this command should be executed
	 * @return result of the check
	 */
	@Override
	public boolean canBeExecuted(IModel game) {
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState toolCardState = (ToolCardState) data.getGameState();

		int index = toolCardState.getIndex();

		return getCallerID()==game.getMainBoard().getData().getCurrentPlayer() &&
				game.getPlayerBoard(getCallerID()).getDicePlacedFrame().getData().getDice(y, x)!=null &&
				(index == CARD2 || index == CARD3 || index == CARD4 || index == CARD12);

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {

		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();

		ToolCardState toolCardState = (ToolCardState)game.getMainBoard().getData().getGameState();
		if((toolCardState.getIndex()==CARD4 || toolCardState.getIndex()==CARD12) &&
				map.containsKey(XPOS) && map.containsKey(YPOS))
		{
			game.getMainBoard().setParamToolCard(YPOS2, y);
			game.getMainBoard().setParamToolCard(XPOS2, x);
		}
		else
		{
			game.getMainBoard().setParamToolCard(YPOS, y);
			game.getMainBoard().setParamToolCard(XPOS, x);
		}
	}

}
