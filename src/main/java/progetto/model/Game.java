package progetto.model;


/**
 * Current state of the model
 */
public class Game implements IModel{

	public static final int MAX_NUM_PLAYERS = 4;

	private final CommandQueue commandQueue = new CommandQueue();

	private final RoundTrack roundTrack = new RoundTrack();

	private final PlayerBoard[] playerBoard = new PlayerBoard[MAX_NUM_PLAYERS];

	private final MainBoard mainBoard = new MainBoard();

	private final DiceBag diceBag = new DiceBag();

	private final RNGenerator rnGenerator = new RNGenerator(0);


	/**
	 * Constructor
	 */
	public Game()
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






}
