package progetto.view.commandline.commands;

import progetto.IClientController;
import progetto.model.IModel;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.ICommand;
import progetto.view.commandline.states.AbstractCLViewState;

/**
 * Abstract class extended by every command of the command line view
 */
public abstract class AbstractCLViewCommand implements ICommand{

    private CommandLineView commandLineView;
    private int index = -1;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modifiy
     */
    public AbstractCLViewCommand(CommandLineView commandLineView){
        this.commandLineView = commandLineView;
    }

    /**
     * get the current command line view
     * @return the current command line view
     */
    public CommandLineView getCommandLineView() {
        return commandLineView;
    }

    /**
     * get the controller attached to the current command line view
     * @return the controller attached to the current command line view
     */
    public IClientController getController(){
        return commandLineView.getController();
    }

    /**
     * get the current model
     * @return the current model
     */
    public IModel getModel(){
        return commandLineView.getController().getModel();
    }

    /**
     * Execute the actions associated to the command
     * @param args input by the user ( it could be null for some commands )
     */
    public abstract void exec(String[] args);

    /**
     * Override of the basic method execute which "execute" a generic command
     * @param params input by the user (it could be null for some commands )
     * @return nothing
     */
    @Override
    public final String execute(String[] params) {
        exec(params);
        return "";
    }

    /**
     * Set the index used to call this command
     * @param index used to call this command
     */
    public final void setName(int index)
    {
        this.index = index;
    }

    /**
     * return the index used to call this command
     * @return the index used to call this command
     */
    @Override
    public final String getName() {
        return index+"";
    }

    /**
     * Set the current state for the command line view
     * @param state to be set
     */
    protected void setState(AbstractCLViewState state)
    {
        getCommandLineView().setState(state);
    }

    /**
     * Method used to write something on the stdout
     * @param s string containing what must be written
     */
    public void write(String s)
    {
        getCommandLineView().write(s);
    }
}
