package progetto.game;

public class GameStartedState extends AbstractGameState
{
	public GameStartedState() {
		super("Game started");
	}

	@Override
	void apply(Game game)
	{
		//First player selection
		int firstPlayer = game.getRNGenerator().getNextInt(game.getMainBoard().getMainBoardData().getPlayerCount());
		game.getMainBoard().setCurrentFirstPlayer(firstPlayer);
		game.getMainBoard().setCurrentPlayer(firstPlayer);
		game.getMainBoard().setCurrentRound(0);

		game.setState(new StartRoundState());
	}

}
