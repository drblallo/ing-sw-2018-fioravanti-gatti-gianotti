package progetto.view.commandline;

import progetto.view.commandline.states.AbstractCLViewState;

import java.util.List;

public class CLCommandProcessor implements ICommandProcessor{

	private AbstractCLViewState currentState;

	@Override
	public void registerCommand(ICommand command) {
		currentState.registerCommand(command);
	}

	@Override
	public void deregisterCommand(ICommand command) {
		currentState.deregisterCommand(command);
	}

	@Override
	public void deregisterCommand(String name) {
		currentState.deregisterCommand(name);
	}

	@Override
	public boolean existCommand(String name) {
		return currentState.existCommand(name);
	}

	@Override
	public boolean existCommand(ICommand command) {
		return currentState.existCommand(command);
	}

	@Override
	public String execute(String params) {
		return currentState.execute(params);
	}

	@Override
	public List<ICommand> getList() {
		return currentState.getList();
	}

	@Override
	public ICommand getCommand(String command) {
		return currentState.getCommand(command);
	}

	@Override
	public String getContent() {
		return currentState.getContent();
	}

	@Override
	public String getName() {
		return currentState.getName();
	}

	@Override
	public String getHelp() {
		return currentState.getHelp();
	}

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

	public AbstractCLViewState getCurrentState()
	{
		return currentState;
	}

	public boolean checkValidity()
	{
		return currentState.isStillValid();
	}
}
