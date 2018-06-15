package progetto.controller;

import progetto.model.AbstractGameAction;
import progetto.model.Model;
import progetto.model.ObservableModel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController implements IGameController {

	private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
	private ArrayList<Integer> pastHashCodes = new ArrayList<>();
	private Model game = new Model();

	public GameController()
	{
		super();
		pastHashCodes.add(hashCode());
	}
	/**
	 *
	 * @param action the action that must be added to the command queue
	 */
	public void sendAction(AbstractGameAction action)
	{
		game.getCommandQueue().offer(action);
	}

	/**
	 * processes all action currently in the queue
	 * NOT THREAD SAFE
	 */
	public void processAllPendingAction()
	{
		while (game.getCommandQueue().peekPending() != null)
			processAction();
	}

	/**
	 * process the oldest pending action
	 * NOT THREAD SAFE
	 */
	public void processAction()
	{
		AbstractGameAction action = game.getCommandQueue().pollPending();
		if (action != null && action.canBeExecuted(getModel()))
		{
			LOGGER.log(Level.FINE, "trying to execute a action");
			action.execute(game);
			pastHashCodes.add(hashCode());
		}
		else
		{
			LOGGER.log(Level.FINE, "A action could not be executed.");
		}
	}

	public int getHash(int index)
	{
		return pastHashCodes.get(index);
	}

	@Override
	public int hashCode()
	{
		return 0;
	}

	@Override
	public boolean equals(Object other)
	{
		if (other == null)
			return false;
		if (other.getClass() != getClass())
			return false;
		GameController g= (GameController) other;
		return hashCode() == g.hashCode();
	}

	public Model getModel()
	{
		return game;
	}

	@Override
	public ObservableModel getObservable() {
		return getModel();
	}

}
