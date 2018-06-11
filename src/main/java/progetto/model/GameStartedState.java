package progetto.model;

/**
 * State used when game has started
 */
public class GameStartedState extends AbstractGameState
{
	public GameStartedState() {
		super("Game started");
	}

	/**
	 * Execute state-related operations
	 * First player selection
	 * @param game
	 */
	@Override
	void apply(Model game)
	{
		int firstPlayer = game.getRNGenerator().getNextInt(game.getMainBoard().getData().getPlayerCount());
		game.getMainBoard().setCurrentFirstPlayer(firstPlayer);
		game.getMainBoard().setCurrentPlayer(firstPlayer);
		game.getMainBoard().setCurrentRound(0);

		game.setState(new StartRoundState());
	}

}
