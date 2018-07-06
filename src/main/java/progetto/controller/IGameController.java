package progetto.controller;

import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.model.ObservableModel;

/**
 * @author Michele
 */
public interface IGameController
{
	void sendAction(AbstractGameAction action);
	void processAllPendingAction();
	void processAction();
	IModel getModel();
	ObservableModel getObservable();
}
