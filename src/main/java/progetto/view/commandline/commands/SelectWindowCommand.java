package progetto.view.commandline.commands;

import progetto.controller.FrameSetAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.WaitingState;

public class SelectWindowCommand extends AbstractStateSwitcherCommand {

    private int number;

    public SelectWindowCommand(CommandLineView commandLineView, int number) {
        super(commandLineView, new WaitingState(commandLineView));
        this.number = number;
    }

    @Override
    protected void perform(String[] params) {
        getController().sendAction(new FrameSetAction(getController().getChair(),
                (number-1)/2, (number-1)%2));
    }

    @Override
    public String getHelp() {

        return "Scegli vetrata numero " + getName();
    }
}
