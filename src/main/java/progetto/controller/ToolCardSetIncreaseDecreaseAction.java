package progetto.controller;

import progetto.model.*;

public class ToolCardSetIncreaseDecreaseAction extends AbstractExecutibleGameAction{

	private int increaseDecrease;

	private static final int CARD1 = 1;

	public  ToolCardSetIncreaseDecreaseAction()
	{
		super();
	}

	/**
	 * Constructor
	 * @param increaseDecrease  0 = increase, 1 = decrease
	 */
	public ToolCardSetIncreaseDecreaseAction(int nPlayer, int increaseDecrease)
	{
		super(nPlayer);
		this.increaseDecrease = increaseDecrease;
	}

	@Override
	public boolean canBeExecuted(Model game)
	{
		MainBoardData data = game.getMainBoard().getData();

		if(data.getGameState().getClass() != ToolCardState.class)
		{
			return false;
		}

		ToolCardState toolCardState = (ToolCardState) data.getGameState();

		int index = toolCardState.getIndex();

		return (increaseDecrease == 0 || increaseDecrease == 1) &&
				getCallerID()==game.getMainBoard().getData().getCurrentPlayer() &&
				index == CARD1;

	}

	@Override
	public void execute(Model game)
	{
		game.getMainBoard().setParamToolCard("increaseDecrease", increaseDecrease);
	}

}
