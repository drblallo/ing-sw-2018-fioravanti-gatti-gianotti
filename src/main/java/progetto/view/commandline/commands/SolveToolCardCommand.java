package progetto.view.commandline.commands;

import progetto.controller.ExecuteToolCardAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

public class SolveToolCardCommand extends AbstractCLViewCommand {

    public SolveToolCardCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] params) {
        ExecuteToolCardAction executeToolCardAction = new ExecuteToolCardAction(getController().getChair());
        if(executeToolCardAction.canBeExecuted(getModel())){
            getController().sendAction(executeToolCardAction);
            setState(new GameTransitionState(getCommandLineView()));
        }
        else write("Inserisci tutti i dati necessari");
    }

    @Override
    public String getHelp() {
        return "Usa carta";
    }
}
