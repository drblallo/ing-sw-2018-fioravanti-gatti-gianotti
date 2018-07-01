package progetto.view.commandline.commands;

import progetto.controller.CancelToolCardUseAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.GameTransitionState;

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
