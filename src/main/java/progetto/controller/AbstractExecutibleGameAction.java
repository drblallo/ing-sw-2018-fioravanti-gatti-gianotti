package progetto.controller;

import progetto.model.AbstractGameAction;

/**
 * Actions extends this abstract
 * @author Michele
 */
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
	 * @param callerID ID of the caller
	 */
	protected AbstractExecutibleGameAction(int callerID)
	{
		super(callerID);
	}

}
