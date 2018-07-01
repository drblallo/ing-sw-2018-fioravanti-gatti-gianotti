package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ContinueCommand;
import progetto.view.commandline.commands.NewGameCommand;
import progetto.view.commandline.commands.SetNameCommand;

/**
 * First menu shown at the beginning of a game
 */
public class DefaultViewState extends AbstractCLViewState {

    public DefaultViewState(CommandLineView view)
    {
        super("Default view state", view);
    }

	@Override
	public void onApply(){

		registerCommand(new NewGameCommand(getView()));
		registerCommand(new ContinueCommand(getView()));
		registerCommand(new SetNameCommand(getView()));

	}

	@Override
	public String getMessage(){

		return "\nBenvenuto in Sagrada!\n";

	}

}
