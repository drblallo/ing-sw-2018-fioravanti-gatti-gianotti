package progetto.integration.client.view.cl;

import progetto.integration.client.IClientController;
import progetto.integration.client.view.AbstractView;
import progetto.model.MainBoardData;
import progetto.network.RoomView;
import progetto.view.commandline.IExecutible;

import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private void checkValidity()
	{
		if (!currentState.checkValidity()) {
			LOGGER.log(Level.SEVERE, "current state was invalid");
			clearQueue();
			setState(new GameTransitionState(this));
		}
	}

	public CommandLineView(IClientController controller, PrintStream printStream)
	{
		super(controller);
		this.printStream = printStream;
		isVisibile = true;
		playerName = "Luca";
		setState(new DefaultViewState(this));
		controller.getRoomViewCallback().addObserver(this::onRoomChanged);
	}

	public boolean getIsRunning(){
		return isRunning;
	}

	@Override
	public void setVisible(boolean visible) {
		isVisibile = visible;
	}

	@Override
	public synchronized void onGameChanged() {
		//nothing to do when game changes
	}

	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}

	public String getPlayerName(){
		return playerName;
	}

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

	void setState(AbstractCLViewState abstractCLViewState){

		currentState.setCurrentState(abstractCLViewState);
		if(currentState.getCurrentState() == abstractCLViewState)
			currentState.getCurrentState().write(
					currentState.getCurrentState().getMessage() + currentState.getContent()+"\n"
			);

		LOGGER.log(Level.FINE, "Current state is: {0}", getAbstractCLViewState().getClass().getSimpleName());
	}

	public AbstractCLViewState getAbstractCLViewState() {
		return currentState.getCurrentState();
	}


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

	public void write(String s)
	{
		runLater(() -> {
			if(isVisibile && printStream != null)
				printStream.print(s);
		});
	}

	@Override
	public String execute(String params) {
		runLater( () -> write(getAbstractCLViewState().execute(params)+"\n"));
		return "";
	}

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

	void processAllPendings(){
		do {
			processPending();
		}while (queue.peek() != null);

	}

	public void run()
	{
		while (isRunning)
			processAllPendings();
	}

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

	public void stop(){
		runLater(()->isRunning = false);
		setVisible(false);
	}
}
