package progetto.model;

/**
 * State used when game has started
 * @author Michele
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

		RoundInformation roundInformation = game.getRoundInformation();

		roundInformation.setCurrentFirstPlayer(firstPlayer);
		roundInformation.setCurrentPlayer(firstPlayer);
		roundInformation.setCurrentRound(0);

		game.setState(new StartRoundState());
	}

}
