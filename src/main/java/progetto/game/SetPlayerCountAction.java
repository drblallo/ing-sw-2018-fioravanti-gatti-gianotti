package progetto.game;

public class SetPlayerCountAction extends AbstractGameAction
{
	private final int playerCount;

	public SetPlayerCountAction(int playerCount)
	{
		super(-1);
		this.playerCount = playerCount;
	}

	SetPlayerCountAction()
	{
		super();
		this.playerCount = 1;
	}

	@Override
	public boolean canBeExecuted(Game game) {
		return (game.getGameState().getClass() == PreGameState.class);
	}

	@Override
	protected void execute(Game game) {
		game.setPlayerCount(playerCount);
	}
}
