package progetto.view.commandline;

/**
 * @author Federica
 */
public interface ICommand {

	String getName();
	String getHelp();
	String execute(String[] params);
}
