package progetto.view.commandline.states;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ReturnCommand;

/**
 * State where the user can decide if he wants to close the game or not
 */
public class ConfirmExitState extends AbstractCLViewState {

    /**
     * public constructor
     * @param cl the command line view that this state will be applied to
     */
    public ConfirmExitState(CommandLineView cl) {
        super("ConfirmExistState", cl);
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        registerCommand(new ReturnCommand(getView(), new DefaultViewState(getView()), "Si"));
        registerCommand(new ReturnCommand(getView(), new RoundViewState(getView()), "No"));
    }

    /**
     * returns a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        return "\nSei sicuro di voler abbandoare la partita?\n";
    }
}
