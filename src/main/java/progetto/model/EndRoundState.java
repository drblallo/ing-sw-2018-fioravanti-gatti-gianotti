package progetto.model;

/**
 * State used at the end of a round
 */
public class EndRoundState extends AbstractGameState
{
	private static final int LAST_ROUND = 9;

	/**
	 * Constructor
	 */
	public EndRoundState()
	{
		super("End round");
	}

	/**
	 * Execute state-related operations
	 * @param game
	 */
	@Override
	void apply(Model game) {

		ExtractedDices extractedDices = game.getMainBoard().getExtractedDices();
		MainBoard mainBoard = game.getMainBoard();

		while(extractedDices.getData().getNumberOfDices()>0)
		{
			game.getRoundTrack().add(extractedDices.removeDice(0), mainBoard.getData().getCurrentRound());
		}

		int round = mainBoard.getData().getCurrentRound();

		if(round == LAST_ROUND)
		{
			game.setState(new EndGameState());
		}
		else
		{
			mainBoard.setCurrentRound(round+1);

			int firstPlayer = mainBoard.getData().getCurrentFirstPlayer();
			int nPlayer = mainBoard.getData().getPlayerCount();
			firstPlayer = (firstPlayer+1)%nPlayer;
			mainBoard.setCurrentFirstPlayer(firstPlayer);
			mainBoard.setCurrentPlayer(firstPlayer);

			game.setState(new StartRoundState());
		}

	}
}
