package progetto.model;


/**
 * Current state of the model
 */
public class Model implements IModel{

	public static final int MAX_NUM_PLAYERS = 4;

	private final CommandQueue commandQueue = new CommandQueue();

	private final RoundTrack roundTrack = new RoundTrack();

	private final PlayerBoard[] playerBoard = new PlayerBoard[MAX_NUM_PLAYERS];

	private final MainBoard mainBoard = new MainBoard();

	private final RoundInformation roundInformation = new RoundInformation();

	private final DiceBag diceBag = new DiceBag();

	private final RNGenerator rnGenerator = new RNGenerator(0);


	/**
	 * Constructor
	 */
	public Model()
	{
		for(int i=0; i<MAX_NUM_PLAYERS; i++)
		{
			playerBoard[i] = new PlayerBoard();
		}
	}

	/**
	 * Get commandQueue
	 * @return commandQueue
	 */
	public CommandQueue getCommandQueue()
	{
		return commandQueue;
	}

	/**
	 * Get roundTrack
	 * @return roundTrack
	 */
	public RoundTrack getRoundTrack()
	{
		return roundTrack;
	}

	/**
	 * Get playerBoard of player index
	 * @param index player number
	 * @return playerBoard[index]
	 */
	public PlayerBoard getPlayerBoard(int index)
	{
		return playerBoard[index];
	}

	/**
	 * Get mainBoard
	 * @return mainBoard
	 */
	public MainBoard getMainBoard()
	{
		return mainBoard;
	}

	/**
	 * Get roundInformation
	 * @return roundInformation
	 */
	public RoundInformation getRoundInformation()
	{
		return roundInformation;
	}

	/**
	 * Get diceBag
	 * @return diceBag
	 */
	public DiceBag getDiceBag()
	{
		return diceBag;
	}

	/**
	 * Get RNGenerator
	 * @return rnGenerator
	 */
	public RNGenerator getRNGenerator()
	{
		return rnGenerator;
	}

	/**
	 * Set seet of RNGenerator
	 * @param seed new value to set
	 */
	public void setSeed(long seed)
	{
		rnGenerator.setSeed(seed);
	}

	/**
	 * Set model state, execute state operations
	 * @param gameState new state to set
	 */
	public void setState(AbstractGameState gameState)
	{
		mainBoard.setGameState(gameState);
		gameState.apply(this);
	}

	/**
	 * Get seed
	 * @return RNGenerator seed
	 */
	long getSeed()
	{
		return rnGenerator.getSeed();
	}

	/**
	 * Evaluate frame of player player
	 * @param player player number
	 * @return score
	 */
	public int evaluatePlayerFrame(int player)
	{

		int playerCount = getMainBoard().getData().getPlayerCount();

		if(player<0 || player >= playerCount)
		{
			return 0;
		}

		if(playerCount == 1)
		{
			return evaluateSinglePlayer();
		}

		return evaluateMultiPlayer(player);

	}

	/**
	 * Evaluate Target Score (SinglePlayer)
	 * @return sum of the remaining dice on the round track
	 */
	public int evaluateTargetScore()
	{
		if(getMainBoard().getData().getPlayerCount() != 1)
		{
			return 0;
		}

		int score = 0;
		final int N_ROUND = 10;

		RoundTrackData roundTrackData = getRoundTrack().getData();

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
	private int evaluateSinglePlayer()
	{
		if(getMainBoard().getData().getPlayerCount()!=1 ||
				getMainBoard().getData().getPublicObjectiveCards().size()<2 ||
				getPlayerBoard(0).getData().getPrivateObjectiveCard().size()<2)
		{
			return 0;
		}

		final int N_CELLS = 20;
		final int PV_PENALTY_PER_CELL = 3;

		PlayerBoard plBoard = getPlayerBoard(0);
		DicePlacedFrame dicePlacedFrame = plBoard.getDicePlacedFrame();

		int scorePR1 = plBoard.getData().getPrivateObjectiveCard().get(0).evaluateFrame(dicePlacedFrame);
		int scorePR2 = plBoard.getData().getPrivateObjectiveCard().get(1).evaluateFrame(dicePlacedFrame);

		int scorePU1 = mainBoard.getData().getPublicObjectiveCards().get(0).evaluateFrame(dicePlacedFrame);
		int scorePU2 = mainBoard.getData().getPublicObjectiveCards().get(1).evaluateFrame(dicePlacedFrame);

		int penalty = PV_PENALTY_PER_CELL * (N_CELLS - dicePlacedFrame.getData().getNDices());

		return (scorePU1 + scorePU2 + Math.max(scorePR1, scorePR2) - penalty);

	}

	/**
	 * Evaluate frame of player player (multi player game)
	 * @param player
	 * @return
	 */
	private int evaluateMultiPlayer(int player)
	{
		if(getPlayerBoard(player).getData().getPrivateObjectiveCard().size()!=1)
		{
			return 0;
		}

		final int N_CELLS = 20;

		PlayerBoard plBoard = getPlayerBoard(player);
		DicePlacedFrame dicePlacedFrame = plBoard.getDicePlacedFrame();

		int scorePR = plBoard.getData().getPrivateObjectiveCard().get(0).evaluateFrame(dicePlacedFrame);
		int scorePU = 0;

		for(int i=0; i<mainBoard.getData().getPublicObjectiveCards().size(); i++)
		{
			scorePU = scorePU + mainBoard.getData().getPublicObjectiveCards().get(i).evaluateFrame(dicePlacedFrame);
		}

		int penalty = (N_CELLS - dicePlacedFrame.getData().getNDices()) + plBoard.getData().getToken();

		return scorePR + scorePU - penalty;

	}

	/**
	 *  get index of winner player. If two player have the same score, return one.
	 * @return index of winner (-1 if it was a single player game and the player has lost)
	 */
	public int getWinner()
	{
		int playerCount = getMainBoard().getData().getPlayerCount();
		if(playerCount == 1)
		{
			int score = evaluateSinglePlayer();
			int target = evaluateTargetScore();

			if(score >= target)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
		else
		{
			int max = evaluateMultiPlayer(0);
			int winner = 0;
			int score;

			for(int i=1; i<playerCount; i++)
			{
				score = evaluateMultiPlayer(i);
				if(score > max)
				{
					max = score;
					winner = i;
				}
			}

			return winner;
		}
	}

}
