package progetto.model;

public class GameStartedState extends AbstractGameState
{
	public GameStartedState() {
		super("Game started");
	}

	@Override
	void apply(Model game)
	{
		//First player selection
		int firstPlayer = game.getRNGenerator().getNextInt(game.getMainBoard().getData().getPlayerCount());
		game.getMainBoard().setCurrentFirstPlayer(firstPlayer);
		game.getMainBoard().setCurrentPlayer(firstPlayer);
		game.getMainBoard().setCurrentRound(0);

		game.setState(new StartRoundState());
	}

}
