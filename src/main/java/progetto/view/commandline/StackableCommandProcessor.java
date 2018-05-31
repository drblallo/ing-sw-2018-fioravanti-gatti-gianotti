package progetto.view.commandline;

public class StackableCommandProcessor extends CommandProcessor
{
	private final StackCommandProcessor commandProcessor;
	private final String pushingMessage;

	public StackableCommandProcessor(String name, StackCommandProcessor stc, String pushingMessage)
	{
		super(name);
		commandProcessor = stc;
		this.pushingMessage = pushingMessage;
	}

	@Override
	public String execute(String[] args)
	{

		if (commandProcessor.peekCommandProcessor() == this)
			return super.execute(args);

		commandProcessor.pushProcessor(this);

		StringBuilder s = new StringBuilder(pushingMessage).append("\n");

		for (ICommand c : getList())
			s.append(c.getName()).append("\n");

		return s.toString();
	}
}
