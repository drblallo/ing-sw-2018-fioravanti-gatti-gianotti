package progetto.view.commandline;

import java.util.List;

/**
 * A command processor must implement this interface
 * @author Federica
 */
public interface ICommandProcessor extends ICommand, IExecutible
{
	/**
	 * register a command into this command processor
	 * @param command to be registered
	 */
	void registerCommand(ICommand command);

	/**
	 * remove a command from this command processor
	 * @param command to be unregistered
	 */
	void deregisterCommand(ICommand command);

	/**
	 * remove a command from this command processor
	 * @param name name of the command to be unregistered
	 */
	void deregisterCommand(String name);

	/**
	 * check if there is a command inside this command processor
	 * @param name name of the command to check
	 * @return true if this command processor contains the command
	 */
	boolean existCommand(String name);

	/**
	 * check if there is a command inside this command processor
	 * @param command command to check
	 * @return true if this command processor contains the command
	 */
	boolean existCommand(ICommand command);

	/**
	 *
	 * @return a list of commands
	 */
	List<ICommand> getList();

	/**
	 *
	 * @param command name of the required command
	 * @return the required command
	 */
	ICommand getCommand(String command);

	/**
	 *
	 * @return a string with all the name of the commands contained by this command processor
	 */
	String getContent();

}
