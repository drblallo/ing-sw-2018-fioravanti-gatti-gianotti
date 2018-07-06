package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.AbstractCLViewState;

/**
 * Abstract class extended by a group of commands which need to change the state of the command line view
 * after their execution
 * @author Federica
 */
public abstract class AbstractStateSwitcherCommand extends AbstractCLViewCommand{

    private AbstractCLViewState abstractCLViewState;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modifiy
     * @param abstractCLViewState the state in which the command line view needs to go after the execution of
     *                            this command
     */
    public AbstractStateSwitcherCommand(CommandLineView commandLineView, AbstractCLViewState abstractCLViewState) {

        super(commandLineView);
        this.abstractCLViewState = abstractCLViewState;

    }

    /**
     * Execute the actions associated to the command
     * @param params input by the user ( it could be null for some commands )
     */
    protected abstract void perform(String[] params);

    /**
     * Override of the basic method execute which "execute" a generic command
     * @param params input by the user (it could be null for some commands )
     */
    @Override
    public final void exec(String[] params)
    {
        perform(params);
        setState(abstractCLViewState);
    }

}
