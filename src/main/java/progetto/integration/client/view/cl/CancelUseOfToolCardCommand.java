package progetto.integration.client.view.cl;

import progetto.controller.CancelToolCardUseAction;

public class CancelUseOfToolCardCommand extends AbstractStateSwitcherCommand {

    public CancelUseOfToolCardCommand(CommandLineView commandLineView) {
        super(commandLineView, new GameTransitionState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {
        getController().sendAction(new CancelToolCardUseAction(getController().getChair()));
    }

    @Override
    public String getHelp() {
        return "Annulla uso della carta scelta";
    }
}
