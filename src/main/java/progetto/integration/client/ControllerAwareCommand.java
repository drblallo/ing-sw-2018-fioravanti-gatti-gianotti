package progetto.integration.client;

import progetto.view.commandline.ICommand;

public class ControllerAwareCommand implements ICommand{

	private final String name;
	private final ClientController controller;
	private final String helpString;

	public ControllerAwareCommand
			(
			String name,
			ClientController controller,
			String helpString,
			IControllerExecutible exec
			)
	{
		this.name = name;
		this.controller = controller;
		this.helpString = helpString;
		this.exec = exec;
	}

	private final IControllerExecutible exec;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getHelp() {
		return helpString;
	}

	@Override
	public String execute(String[] params) {
		return exec.execute(controller);
	}
}
