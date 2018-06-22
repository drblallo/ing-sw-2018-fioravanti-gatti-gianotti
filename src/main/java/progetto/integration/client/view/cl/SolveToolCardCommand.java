package progetto.integration.client.view.cl;

import progetto.controller.ExecuteToolCard1Action;

public class SolveToolCardCommand extends AbstractCLViewCommand {

    private int numberOfCommand;

    public SolveToolCardCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
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
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Usa carta";
    }
}
