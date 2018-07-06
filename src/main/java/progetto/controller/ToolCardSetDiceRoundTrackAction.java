package progetto.controller;

import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

/**
 * Action to select a dice from round track
 * @author Michele
 */
public class ToolCardSetDiceRoundTrackAction extends AbstractExecutibleGameAction{

	private final int round;
	private final int nDiceRT;

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
	 * @param callerID callerID
	 * @param round id of the round
	 * @param nDiceRT position of the selected dice in the selected round
	 */
	public ToolCardSetDiceRoundTrackAction(int callerID, int round, int nDiceRT)
	{
		super(callerID);
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

		return getCallerID()==game.getRoundInformation().getData().getCurrentPlayer() &&
				game.getRoundTrack().getData().getDice(round, nDiceRT)!=null ;

	}

	/**
	 * Execute action
	 * @param game the model in which we want to execute this command
	 */
	@Override
	public void execute(Model game) {
		game.getRoundInformation().setRound(round);
		game.getRoundInformation().setNDiceRT(nDiceRT);
	}

}
