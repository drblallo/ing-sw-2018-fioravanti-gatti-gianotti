package progetto.view.commandline.commands;

import progetto.controller.ExecuteToolCardAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

/**
 * Command to solve a tool card
 * @author Federica
 */
public class SolveToolCardCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view associated to this command
     */
    public SolveToolCardCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * Check if the ExecuteToolCardAction can be executed and if positive send the action to the controller
     * @param params input not needed
     */
    @Override
    public void exec(String[] params) {
        ExecuteToolCardAction executeToolCardAction = new ExecuteToolCardAction(getController().getChair());
        if(executeToolCardAction.canBeExecuted(getModel())){
            getController().sendAction(executeToolCardAction);
            setState(new GameTransitionState(getCommandLineView()));
        }
        else write("Inserisci tutti i dati necessari");
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Usa carta";
    }
}
