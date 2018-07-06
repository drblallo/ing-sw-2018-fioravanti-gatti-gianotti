package progetto.view.commandline;

/**
 * A command must implement this interface
 * @author Federica
 */
public interface ICommand {

	/**
	 *
	 * @return the name of this command
	 */
	String getName();

	/**
	 *
	 * @return infos about what this command does
	 */
	String getHelp();

	/**
	 *
	 * @param params recived message
	 * @return if necessary, return the string that must be sent the the user
	 */
	String execute(String[] params);
}
