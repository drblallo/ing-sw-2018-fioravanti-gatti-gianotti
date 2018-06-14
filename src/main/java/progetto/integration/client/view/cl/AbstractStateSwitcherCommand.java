package progetto.integration.client.view.cl;

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
