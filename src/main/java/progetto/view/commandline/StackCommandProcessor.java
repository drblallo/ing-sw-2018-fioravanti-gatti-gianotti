package progetto.view.commandline;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StackCommandProcessor implements ICommandProcessor
{
	private Deque<StackableCommandProcessor> stack = new LinkedList<>();

	@Override
	public void registerCommand(ICommand command) {
		peekCommandProcessor().registerCommand(command);
	}

	@Override
	public void deregisterCommand(ICommand command) {
		peekCommandProcessor().deregisterCommand(command);
	}

	@Override
	public void deregisterCommand(String name) {
		peekCommandProcessor().deregisterCommand(name);
	}

	@Override
	public boolean existCommand(String name) {
		return peekCommandProcessor().existCommand(name);
	}

	@Override
	public boolean existCommand(ICommand command) {
		return peekCommandProcessor().existCommand(command);
	}

	@Override
	public String execute(String params) {
		return peekCommandProcessor().execute(params);
	}

	@Override
	public List<ICommand> getList() {
		return peekCommandProcessor().getList();
	}

	@Override
	public ICommand getCommand(String command) {
		return peekCommandProcessor().getCommand(command);
	}

	@Override
	public String getName() {
		return peekCommandProcessor().getName();
	}

	@Override
	public String getHelp() {
		return peekCommandProcessor().getHelp();
	}

	@Override
	public String execute(String[] params) {
		return peekCommandProcessor().execute(params);
	}

	public void pushProcessor(StackableCommandProcessor processor)
	{
		stack.push(processor);
	}

	public StackableCommandProcessor popCommandProcessor()
	{
		return stack.pop();
	}

	public StackableCommandProcessor peekCommandProcessor()
	{
		if (stack.isEmpty())
			return null;
		return stack.peek();
	}
}
