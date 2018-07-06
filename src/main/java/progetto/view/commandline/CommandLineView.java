package progetto.view.commandline;

import progetto.AbstractView;
import progetto.IClientController;
import progetto.model.MainBoardData;
import progetto.network.RoomView;
import progetto.view.commandline.states.AbstractCLViewState;
import progetto.view.commandline.states.DefaultViewState;
import progetto.view.commandline.states.GameTransitionState;

import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Federica
 *
 * handles everything related to the command live view.
 * handles threading
 */
public class CommandLineView extends AbstractView implements IExecutible, Runnable
{
	private static final Logger LOGGER = Logger.getLogger(CommandLineView.class.getName());
	private CLCommandProcessor currentState = new CLCommandProcessor();
	private String playerName;
	private RoomView oldRoomView;
	private boolean isVisibile;
	private PrintStream printStream;
	private static final int TIMEOUT = 200;

	private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
	private boolean isRunning = true;

	/**
	 * check if the current state is till valid
	 */
	private void checkValidity()
	{
		if (!currentState.checkValidity()) {
			LOGGER.log(Level.SEVERE, "current state was invalid");
			clearQueue();
			setState(new GameTransitionState(this));
		}
	}

	/**
	 * creates the command live view
	 * @param controller the controller this view will be attached to
	 * @param printStream the printstream where this view will write to
	 */
	public CommandLineView(IClientController controller, PrintStream printStream)
	{
		super(controller);
		this.printStream = printStream;
		isVisibile = true;
		playerName = "Luca";
		setState(new DefaultViewState(this));
		controller.getRoomViewCallback().addObserver(this::onRoomChanged);
	}

	/**
	 *
	 * @return if the thread is still running
	 */
	public boolean getIsRunning(){
		return isRunning;
	}

	/**
	 *
	 * @param visible true if the controller wishes you to be visible, false otherwise
	 */
	@Override
	public void setVisible(boolean visible) {
		isVisibile = visible;
	}

	/**
	 * called when the game changes i the controller, returns to the staring view
	 */
	@Override
	public synchronized void onGameChanged() {
		if (!getController().thereIsGame())
			setState(new DefaultViewState(this));
	}

	/**
	 * sets the player name that will be used to join rooms
	 * @param playerName
	 */
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}

	/**
	 *
	 * @return the current name of the player used to log in in to rooms
	 */
	public String getPlayerName(){
		return playerName;
	}

	/**
	 * clears all the queue of operations
	 */
	private void clearQueue()
	{
		while (queue.peek() != null)
		{
			try
			{
				queue.take();
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				LOGGER.log(Level.SEVERE, "FAILED TO CLEAR QUEUE");
				stop();
			}
		}
	}

	/**
	 * sets the current state
	 * @param abstractCLViewState the state to be set
	 */
	public void setState(AbstractCLViewState abstractCLViewState){

		currentState.setCurrentState(abstractCLViewState);
		if(currentState.getCurrentState() == abstractCLViewState)
			currentState.getCurrentState().write(
					currentState.getCurrentState().getMessage() + currentState.getContent()+"\n"
			);

		LOGGER.log(Level.FINE, "Current state is: {0}", getAbstractCLViewState().getClass().getSimpleName());
	}

	/**
	 *
	 * @return the current state
	 */
	public AbstractCLViewState getAbstractCLViewState() {
		return currentState.getCurrentState();
	}


	/**
	 *  called when the server room change
	 * @param roomView the new room view
	 */
	private void onRoomChanged(RoomView roomView){
		if(oldRoomView!=null) {
			for (int i = 0; i<MainBoardData.MAX_NUM_PLAYERS; i++)
			{
				if ((oldRoomView.getPlayerOfChair(i) == null) != (roomView.getPlayerOfChair(i) == null))
				{
					write(roomView.toString());
					break;
				}
			}
		}
		oldRoomView = roomView;
	}

	/**
	 *
	 * @param s the string to be written in the output stream
	 */
	public void write(String s)
	{
		runLater(() -> {
			if(isVisibile && printStream != null)
				printStream.print(s);
		});
	}

	/**
	 *
	 * @param params the params read from the input
	 * @return always ""
	 */
	@Override
	public String execute(String params) {
		runLater( () -> write(getAbstractCLViewState().execute(params)+"\n"));
		return "";
	}

	/**
	 * process all the pending
	 */
	private void processPending()
	{
		try
		{
			Runnable runnable = queue.poll(TIMEOUT, TimeUnit.MILLISECONDS);
			if (runnable!=null)
				runnable.run();
			checkValidity();
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			isRunning = false;
			LOGGER.log(Level.SEVERE,"Something went wrong {0}", e.getMessage());
		}
	}

	/**
	 * process all the pending
	 */
	void processAllPendings(){
		do {
			processPending();
		}while (queue.peek() != null);

	}

	/**
	 * process all the pending
	 */
	public void run()
	{
		Thread.currentThread().setName("Command Line Thread");
		while (isRunning)
			processAllPendings();
	}

	/**
	 * run a runnable in the correct thread
	 * @param r the runnable
	 */
	private void runLater(Runnable r)
	{
		try
		{
			queue.put(r);
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			isRunning = false;
			LOGGER.log(Level.SEVERE,"Something went wrong {0}", e.getMessage());
		}
	}

	/**
	 * close the this thread
	 */
	public void stop(){
		runLater(()->isRunning = false);
		setVisible(false);
	}
}
