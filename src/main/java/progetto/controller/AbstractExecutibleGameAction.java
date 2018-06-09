package progetto.controller;

import progetto.model.AbstractGameAction;

public abstract class AbstractExecutibleGameAction extends AbstractGameAction {

	protected AbstractExecutibleGameAction()
	{
		super();
	}

	protected AbstractExecutibleGameAction(int callerID)
	{
		super(callerID);
	}

}
