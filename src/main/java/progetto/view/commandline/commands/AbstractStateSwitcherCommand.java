package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.AbstractCLViewState;

public abstract class AbstractStateSwitcherCommand extends AbstractCLViewCommand{

    private AbstractCLViewState abstractCLViewState;

    public AbstractStateSwitcherCommand(CommandLineView commandLineView, AbstractCLViewState abstractCLViewState) {

        super(commandLineView);
        this.abstractCLViewState = abstractCLViewState;

    }

    protected abstract void perform(String[] params);

    @Override
    public final void exec(String[] params)
    {
        setState(abstractCLViewState);
        perform(params);
    }

}
