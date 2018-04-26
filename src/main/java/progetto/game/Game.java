package progetto.game;

/**
 * Current state of the game
 */
public final class Game {

	private static final int MAX_NUM_PLAYERS = 4;

	private CommandQueue commandQueue = new CommandQueue();

	private RoundTrack roundTrack = new RoundTrack();

	private PlayerBoard[] playerBoard = new PlayerBoard[MAX_NUM_PLAYERS];

	private MainBoard mainBoard = new MainBoard();

	private DiceBag diceBag = new DiceBag();

	private RNGenerator rnGenerator;


	public Game()
	{
		for(int i=0; i<MAX_NUM_PLAYERS; i++)
		{
			playerBoard[i] = new PlayerBoard();
		}
	}

	public CommandQueue getCommandQueue()
	{
		return commandQueue;
	}

	public RoundTrack getRoundTrack()
	{
		return roundTrack;
	}

	public PlayerBoard getPlayerBoard(int index)
	{
		return playerBoard[index];
	}

	public MainBoard getMainBoard()
	{
		return mainBoard;
	}

	public DiceBag getDiceBag()
	{
		return diceBag;
	}

	public void setSeed(long seed)
	{
		rnGenerator = new RNGenerator(seed);
	}

	public long getSeed()
	{
		return rnGenerator.getSeed();
	}

}
