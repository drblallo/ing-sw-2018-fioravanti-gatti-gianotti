package progetto.controller;

import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.model.ObservableModel;

/**
 * Game controller manages everything related to a single game. Every modification to the underlying model
 * can be operated only through send action. It's up to the server to ensure that the correct player is trying
 * to perform the correct operations.
 * @author Michele
 */
public interface IGameController
{
	/**
	 * sends an action that must be later processed and applied to the model
	 * @param action to be sent to be later processed
	 */
	void sendAction(AbstractGameAction action);

	/**
	 * process all the action that where previusly sent to the server
	 */
	void processAllPendingAction();

	/**
	 * process only the most ancient action sent to the server
	 */
	void processAction();

	/**
	 *
	 * @return the model that is currently in the server
	 */
	IModel getModel();

	/**
	 *
	 * @return the observable model of the server, this must be the same for the whole lifetime of the controller
	 */
	ObservableModel getObservable();
}
