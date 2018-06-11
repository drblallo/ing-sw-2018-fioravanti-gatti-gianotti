package progetto.controller;

import progetto.model.AbstractGameAction;

public abstract class AbstractExecutibleGameAction extends AbstractGameAction {

	/**
	 * Constructor without parameters
	 */
	protected AbstractExecutibleGameAction()
	{
		super();
	}

	/**
	 * Constructor to set callerID
	 * @param callerID
	 */
	protected AbstractExecutibleGameAction(int callerID)
	{
		super(callerID);
	}

}
