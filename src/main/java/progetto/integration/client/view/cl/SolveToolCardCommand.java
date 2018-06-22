package progetto.integration.client.view.cl;

import progetto.controller.ExecuteToolCard1Action;

public class SolveToolCardCommand extends AbstractCLViewCommand {

    public SolveToolCardCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] params) {
        ExecuteToolCard1Action executeToolCard1Action = new ExecuteToolCard1Action(getController().getChair());
        if(executeToolCard1Action.canBeExecuted(getModel())){
            getController().sendAction(executeToolCard1Action);
            setState(new GameTransitionState(getCommandLineView()));
        }
        else write("Insiere tutti i dati necessari!");
    }

    @Override
    public String getHelp() {
        return "Usa carta";
    }
}
