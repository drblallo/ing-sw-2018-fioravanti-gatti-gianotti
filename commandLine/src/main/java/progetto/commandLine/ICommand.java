package progetto.commandLine;

public interface ICommand {

	String getName();
	String getHelp();
	String execute(String params);
}
