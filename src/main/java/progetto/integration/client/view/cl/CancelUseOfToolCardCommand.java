package progetto.integration.client.view.cl;

import progetto.controller.CancelToolCardUseAction;

public class CancelUseOfToolCardCommand extends AbstractStateSwitcherCommand {

    private int numberOfCommand;

    public CancelUseOfToolCardCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView, new GameTransitionState(commandLineView));
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    protected void perform(String[] params) {
        getController().sendAction(new CancelToolCardUseAction(getController().getChair()));
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Annulla uso della carta scelta";
    }
}
