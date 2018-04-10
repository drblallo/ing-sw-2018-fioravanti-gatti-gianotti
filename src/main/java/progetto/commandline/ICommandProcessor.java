package progetto.commandline;

public interface ICommandProcessor {

	void registerCommand(ICommand command);
	void deregisterCommand(ICommand command);
	String processCommand(String command);
}
