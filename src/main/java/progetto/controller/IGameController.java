package progetto.controller;

import progetto.model.AbstractGameAction;
import progetto.model.IModel;

public interface IGameController
{
	void sendAction(AbstractGameAction action);
	void processAllPendingAction();
	void processAction();
	IModel getModel();
}
