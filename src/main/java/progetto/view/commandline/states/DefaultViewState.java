package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.CloseGameCommand;
import progetto.view.commandline.commands.ContinueCommand;
import progetto.view.commandline.commands.NewGameCommand;
import progetto.view.commandline.commands.SetNameCommand;

/**
 * First state shown at the beginning of the program
 */
public class DefaultViewState extends AbstractCLViewState {

	/**
	 * public constructor
	 * @param view the command line view that this state will be applied to
	 */
    public DefaultViewState(CommandLineView view)
    {
        super("Default view state", view);
    }

	/**
	 * load the commands associated to this stage
	 */
	@Override
	public void onApply(){

		registerCommand(new NewGameCommand(getView()));
		registerCommand(new ContinueCommand(getView()));
		registerCommand(new SetNameCommand(getView()));
		registerCommand(new CloseGameCommand(getView()));

	}

	/**
	 * returns a message associated to this stage
	 * @return a message associated to this stage
	 */
	@Override
	public String getMessage(){

		return "\nBenvenuto in Sagrada!\n";

	}

}
