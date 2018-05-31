package progetto.controller;

import progetto.model.AbstractGameAction;

public abstract class AbstractExecutibleGameAction extends AbstractGameAction{

	protected AbstractExecutibleGameAction()
	{
		super(-1);
	}

	protected AbstractExecutibleGameAction(int callerID)
	{
		super(callerID);
	}

}
