package progetto.game;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Current state of the game
 */
public final class Game implements IExecuibleGame{

	private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

	private static final int MAX_NUM_PLAYERS = 4;

	private CommandQueue commandQueue = new CommandQueue();

	private RoundTrack roundTrack = new RoundTrack();

	private PlayerBoard[] playerBoard = new PlayerBoard[MAX_NUM_PLAYERS];

	private MainBoard mainBoard = new MainBoard();

	private DiceBag diceBag = new DiceBag();

	private RNGenerator rnGenerator = new RNGenerator(0);

	private AbstractGameState gameState = new PreGameState();

	private int playerCount = MAX_NUM_PLAYERS;

	private ArrayList<Integer> pastHashCodes = new ArrayList<>();


	public Game()
	{
		pastHashCodes.add(hashCode());
		for(int i=0; i<MAX_NUM_PLAYERS; i++)
		{
			playerBoard[i] = new PlayerBoard();
		}
	}

	public AbstractProcessor<AbstractGameAction> getCommandQueue()
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

	void setSeed(long seed)
	{
		rnGenerator = new RNGenerator(seed);
	}

	long getSeed()
	{
		return rnGenerator.getSeed();
	}

	/**
	 *
	 * @param action the action that must be added to the command queue
	 */
	public void sendAction(AbstractGameAction action)
	{
		commandQueue.offer(action);
	}

	/**
	 * processes all action currently in the queue
	 * NOT THREAD SAFE
	 */
	public void processAllPendingAction()
	{
		while (commandQueue.peekPending() != null)
			processAction();
	}

	/**
	 * process the oldest pending action
	 * NOT THREAD SAFE
	 */
	public void processAction()
	{
		AbstractGameAction action = commandQueue.pollPending();
		if (action != null && action.canBeExecuted(this))
		{
			LOGGER.log(Level.FINE, "trying to execute a action");
			action.execute(this);
			pastHashCodes.add(hashCode());
		}
		else
		{
			LOGGER.log(Level.FINE, "A action could not be executed.");
		}
	}

	/**
	 *
	 * @return the current game state
	 */
	public AbstractGameState getGameState()
	{
		return gameState;
	}

	/**
	 *
	 * @param state the new state of the game
	 */
	void setGameState(AbstractGameState state)
	{
		gameState = state;
	}

	/**
	 *
	 * @return the current player count
	 */
	public int getPlayerCount()
	{
		return playerCount;
	}

	/**
	 *
	 * @param plc the new player count
	 */
	void setPlayerCount(int plc)
	{
		playerCount = plc;
	}

	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (other.getClass() != getClass())
			return false;
		Game game = (Game) other;
		return hashCode() == game.hashCode();
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	public int getHash(int index)
	{
		return pastHashCodes.get(index);
	}



}
