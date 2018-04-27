package progetto.commandline;

public class ListCommand implements ICommand
{
	private final CommandProcessor target;

	public ListCommand(CommandProcessor target)
	{
		this.target = target;
	}

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String getHelp() {
		return "return the name of all commands";
	}

	/**
	 *
	 * @param params provided parameters, they are ignored
	 * @return a string containing all the name of all existing commands
	 */
	@Override
	public String execute(String[] params)
	{
		StringBuilder s = new StringBuilder("");

		for (ICommand c : target.getList())
			s.append(c.getName()).append("\n");

		return s.toString();
	}
}
