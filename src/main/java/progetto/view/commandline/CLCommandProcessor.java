package progetto.view.commandline;

import progetto.view.commandline.states.AbstractCLViewState;

import java.util.List;

/**
 * decorator of command processor, everything is computed by current state
 * @author Federica
 */
public class CLCommandProcessor implements ICommandProcessor{

	private AbstractCLViewState currentState;

	/**
	 *
	 * @param command to be registered
	 */
	@Override
	public void registerCommand(ICommand command) {
		currentState.registerCommand(command);
	}

	/**
	 *
	 * @param command to be unregistered
	 */
	@Override
	public void deregisterCommand(ICommand command) {
		currentState.deregisterCommand(command);
	}

	/**
	 *
	 * @param name name of the command to be unregistered
	 */
	@Override
	public void deregisterCommand(String name) {
		currentState.deregisterCommand(name);
	}

	/**
	 *
	 * @param name name of the command to check
	 * @return if the command is contained
	 */
	@Override
	public boolean existCommand(String name) {
		return currentState.existCommand(name);
	}

	/**
	 *
	 * @param command command to check
	 * @return if the command is contained
	 */
	@Override
	public boolean existCommand(ICommand command) {
		return currentState.existCommand(command);
	}

	/**
	 *
	 * @param params input
	 * @return if necessary, return the string that must be sent the the user
	 */
	@Override
	public String execute(String params) {
		return currentState.execute(params);
	}

	/**
	 *
	 * @return a list of commands
	 */
	@Override
	public List<ICommand> getList() {
		return currentState.getList();
	}

	/**
	 *
	 * @param command name of the required command
	 * @return the required command
	 */
	@Override
	public ICommand getCommand(String command) {
		return currentState.getCommand(command);
	}

	/**
	 *
	 * @return a string with all the name of the commands contained by this command processor
	 */
	@Override
	public String getContent() {
		return currentState.getContent();
	}

	/**
	 *
	 * @return the name of the current state
	 */
	@Override
	public String getName() {
		return currentState.getName();
	}

	/**
	 *
	 * @return infos about the current state
 	 */
	@Override
	public String getHelp() {
		return currentState.getHelp();
	}

	/**
	 *
	 * @param params recived message
	 * @return if necessary, return the string that must be sent the the user
	 */
	@Override
	public String execute(String[] params) {
		return currentState.execute(params);
	}

	public void setCurrentState(AbstractCLViewState state)
	{
		if (currentState == state)
			return;

		if (currentState != null)
			currentState.onRemove();

		currentState = state;
		currentState.reloadObservers();
		currentState.onApply();

	}

	/**
	 *
	 * @return current state
	 */
	public AbstractCLViewState getCurrentState()
	{
		return currentState;
	}

	/**
	 *
	 * @return if the current state is still valid
	 */
	public boolean checkValidity()
	{
		return currentState.isStillValid();
	}
}
