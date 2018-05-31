package progetto.view.commandline;

public class PopCommand implements ICommand
{
	private final StackCommandProcessor stackCommandProcessor;
	private final String targetName;
	private final String name;

	public PopCommand(StackCommandProcessor stc, String name, String targetName)
	{
		stackCommandProcessor = stc;
		this.targetName = targetName;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getHelp() {
		return "Torna al menu " + targetName;
	}

	@Override
	public String execute(String[] params)
	{
		while (!stackCommandProcessor.peekCommandProcessor().getName().equals(targetName))
			stackCommandProcessor.popCommandProcessor();

		StackableCommandProcessor stc = stackCommandProcessor.popCommandProcessor();
		return stc.execute("");
	}
}
