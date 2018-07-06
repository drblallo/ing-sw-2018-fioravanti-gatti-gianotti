package progetto.model;

/**
 * State for the end of the game
 * Evaluate frames
 * @author Michele
 */
public class EndGameState extends AbstractGameState {

	/**
	 * Constructor
	 */
	public EndGameState()
	{
		super("End model");
	}

	/**
	 * Evaluate frames
	 * Save evaluation in player playerBoardData
	 * @param game game
	 */
	@Override
	public void apply(Model game)
	{
		int playerCount = game.getMainBoard().getData().getPlayerCount();

		if(playerCount == 1)
		{
			game.getPlayerBoard(0).setScore(evaluateSinglePlayer(game));
			game.getMainBoard().setSinglePlayerTarget(evaluateTargetScore(game));
			return;
		}

		for(int player=0; player<playerCount; player++)
		{
			game.getPlayerBoard(player).setScore(evaluateMultiPlayer(game, player));
		}
	}


	/**
	 * Evaluate Target Score (SinglePlayer)
	 * @return sum of the remaining dice on the round track
	 */
	public int evaluateTargetScore(Model game)
	{
		if(game.getMainBoard().getData().getPlayerCount() != 1)
		{
			return 0;
		}

		int score = 0;
		final int N_ROUND = 10;

		RoundTrackData roundTrackData = game.getRoundTrack().getData();

		int round=0;
		int pos;
		while(round<N_ROUND)
		{
			if(!roundTrackData.isFree(round))
			{
				pos = 0;
				do{
					score = score + roundTrackData.getDice(round, pos).getValue().ordinal()+1;
					pos++;
				}while(roundTrackData.getDice(round, pos)!=null);
			}
			round++;
		}
		return score;

	}


	/**
	 * Evaluate Single Player total score
	 * @return score of the player (single player)
	 */
	private int evaluateSinglePlayer(Model game)
	{
		if(game.getMainBoard().getData().getPlayerCount()!=1 ||
				game.getMainBoard().getData().getPublicObjectiveCards().size()<2 ||
				game.getPlayerBoard(0).getData().getPrivateObjectiveCard().size()<2)
		{
			return 0;
		}

		final int N_CELLS = 20;
		final int PV_PENALTY_PER_CELL = 3;

		PlayerBoard plBoard = game.getPlayerBoard(0);
		DicePlacedFrame dicePlacedFrame = plBoard.getDicePlacedFrame();

		int scorePR1 = plBoard.getData().getPrivateObjectiveCard().get(0).evaluateFrame(dicePlacedFrame);
		int scorePR2 = plBoard.getData().getPrivateObjectiveCard().get(1).evaluateFrame(dicePlacedFrame);

		int scorePU1 = game.getMainBoard().getData().getPublicObjectiveCards().get(0).evaluateFrame(dicePlacedFrame);
		int scorePU2 = game.getMainBoard().getData().getPublicObjectiveCards().get(1).evaluateFrame(dicePlacedFrame);

		int penalty = PV_PENALTY_PER_CELL * (N_CELLS - dicePlacedFrame.getData().getNDices());

		return (scorePU1 + scorePU2 + Math.max(scorePR1, scorePR2) - penalty);

	}

	/**
	 * Evaluate frame of player player (multi player game)
	 * @param player
	 * @return
	 */
	private int evaluateMultiPlayer(Model game, int player)
	{
		if(game.getPlayerBoard(player).getData().getPrivateObjectiveCard().size()!=1)
		{
			return 0;
		}

		final int N_CELLS = 20;

		PlayerBoard plBoard = game.getPlayerBoard(player);
		DicePlacedFrame dicePlacedFrame = plBoard.getDicePlacedFrame();

		int scorePR = plBoard.getData().getPrivateObjectiveCard().get(0).evaluateFrame(dicePlacedFrame);
		int scorePU = 0;

		for(int i=0; i<game.getMainBoard().getData().getPublicObjectiveCards().size(); i++)
		{
			scorePU = scorePU + game.getMainBoard().getData().getPublicObjectiveCards().get(i).evaluateFrame(dicePlacedFrame);
		}

		int penalty = (N_CELLS - dicePlacedFrame.getData().getNDices()) + plBoard.getData().getToken();

		return scorePR + scorePU - penalty;

	}

}
