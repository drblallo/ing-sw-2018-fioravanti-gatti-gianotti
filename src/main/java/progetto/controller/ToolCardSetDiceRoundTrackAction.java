package progetto.controller;

import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

/**
 * Action to select a dice from round track
 */
public class ToolCardSetDiceRoundTrackAction extends AbstractExecutibleGameAction{

	private final int round;
	private final int nDiceRT;

	private static final int CARD5 = 5;
	private static final int CARD12 = 12;

	/**
	 * Constructor without parameters
	 */
	public ToolCardSetDiceRoundTrackAction()
	{
		super();
		round = -1;
		nDiceRT = -1;

	}

	/**
	 * Constructor to set values
	 * @param nPlayer callerID
	 * @param round n round
	 * @param nDiceRT n dice of selected round
	 */
	public ToolCardSetDiceRoundTrackAction(int nPlayer, int round, int nDiceRT)
	{
		super(nPlayer);
		this.round = round;
		this.nDiceRT = nDiceRT;

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

		return getCallerID()==game.getRoundInformation().getData().getCurrentPlayer() &&
				game.getRoundTrack().getData().getDice(round, nDiceRT)!=null &&
				(index == CARD5 || index == CARD12);

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getMainBoard().setParamToolCard("round", round);
		game.getMainBoard().setParamToolCard("nDiceRT", nDiceRT);
	}

}
