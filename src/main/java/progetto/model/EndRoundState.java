package progetto.model;

/**
 * State used at the end of a round
 * @author Michele
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
		RoundInformation roundInformation = game.getRoundInformation();

		while(extractedDices.getData().getNumberOfDices()>0)
		{
			game.getRoundTrack().add(extractedDices.removeDice(0), roundInformation.getData().getCurrentRound());
		}

		int round = roundInformation.getData().getCurrentRound();

		if(round == LAST_ROUND)
		{
			game.setState(new EndGameState());
		}
		else
		{
			roundInformation.setCurrentRound(round+1);

			int firstPlayer = roundInformation.getData().getCurrentFirstPlayer();
			int nPlayer = mainBoard.getData().getPlayerCount();
			firstPlayer = (firstPlayer+1)%nPlayer;
			roundInformation.setCurrentFirstPlayer(firstPlayer);
			roundInformation.setCurrentPlayer(firstPlayer);

			game.setState(new StartRoundState());
		}

	}
}
