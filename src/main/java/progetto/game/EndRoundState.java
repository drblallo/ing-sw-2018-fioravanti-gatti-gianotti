package progetto.game;


public class EndRoundState extends AbstractGameState
{
	private static final int LAST_ROUND = 9;

	public EndRoundState()
	{
		super("End round");
	}

	@Override
	void apply(Game game) {

		ExtractedDices extractedDices = game.getMainBoard().getExtractedDices();
		MainBoard mainBoard = game.getMainBoard();

		while(extractedDices.getExtractedDicesData().getNumberOfDices()>0)
		{
			game.getRoundTrack().add(extractedDices.removeDice(0), mainBoard.getMainBoardData().getCurrentRound());
		}

		int round = mainBoard.getMainBoardData().getCurrentRound();

		if(round == LAST_ROUND)
		{
			game.setState(new EndGameState());
		}
		else
		{
			mainBoard.setCurrentRound(round+1);

			int firstPlayer = mainBoard.getMainBoardData().getCurrentFirstPlayer();
			int nPlayer = mainBoard.getMainBoardData().getPlayerCount();
			firstPlayer = (firstPlayer+1)%nPlayer;
			mainBoard.setCurrentFirstPlayer(firstPlayer);
			mainBoard.setCurrentPlayer(firstPlayer);

			game.setState(new StartRoundState());
		}

	}
}
