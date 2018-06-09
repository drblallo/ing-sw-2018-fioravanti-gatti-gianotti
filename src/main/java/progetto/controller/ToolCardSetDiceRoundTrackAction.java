package progetto.controller;

import progetto.model.MainBoardData;
import progetto.model.Model;
import progetto.model.ToolCardState;

public class ToolCardSetDiceRoundTrackAction extends AbstractExecutibleGameAction{

	private final int round;
	private final int nDiceRT;

	private static final int CARD5 = 5;
	private static final int CARD12 = 12;

	public ToolCardSetDiceRoundTrackAction()
	{
		super();
		round = -1;
		nDiceRT = -1;

	}

	public ToolCardSetDiceRoundTrackAction(int nPlayer, int round, int nDiceRT)
	{
		super(nPlayer);
		this.round = round;
		this.nDiceRT = nDiceRT;

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
				game.getRoundTrack().getData().getDice(round, nDiceRT)!=null &&
				(index == CARD5 || index == CARD12);

	}

	@Override
	public void execute(Model game) {
		game.getMainBoard().setParamToolCard("round", round);
		game.getMainBoard().setParamToolCard("nDiceRT", nDiceRT);
	}

}
