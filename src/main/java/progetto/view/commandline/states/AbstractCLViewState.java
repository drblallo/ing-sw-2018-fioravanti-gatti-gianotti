package progetto.view.commandline.states;

import progetto.IClientController;
import progetto.model.IModel;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.CommandProcessor;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.AbstractCLViewCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class extended by every state of the command line view
 */
public abstract class AbstractCLViewState extends CommandProcessor
{

    private final CommandLineView view;
    private final List<DifferenceDescriptor> differenceDescriptors = new ArrayList<>();

    /**
     * public constructor
     * @param name name of the state
     * @param cl the command line view that this state will be applied to
     */
    public AbstractCLViewState(String name, CommandLineView cl)
    {
        super(name);
        view = cl;
    }

    /**
     * Register a command into this state
     * @param command command to be registered
     */
    public void registerCommand(AbstractCLViewCommand command)
    {
       command.setName(getCommandCount() + 1);
       super.registerCommand(command);
    }

    /**
     * get the controller attached to this command line view
     * @return the controller attached to this command line view
     */
    public IClientController getController(){
        return view.getController();
    }

    /**
     * get the current model
     * @return the current model
     */
    public IModel getModel(){
        return view.getController().getModel();
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    public boolean isStillValid(){
        return true;
    }

    /**
     * add difference descriptors to this state
     */
    public void addObservers(){
        //
    }

    /**
     * clear old difference descriptors and add the new ones
     */
    public void reloadObservers()
    {
        for (DifferenceDescriptor d: differenceDescriptors)
            d.detach();

        differenceDescriptors.clear();
        addObservers();
    }

    /**
     * remove difference descriptors from this state
     */
    public final void onRemove()
    {
        for (DifferenceDescriptor d: differenceDescriptors)
            d.detach();

        differenceDescriptors.clear();
    }

    /**
     * return the list of the difference descriptors of this state
     * @return the list of the difference descriptors of this state
     */
    protected List<DifferenceDescriptor> getDifferenceDescriptors()
    {
        return differenceDescriptors;
    }

    /**
     * Does something before showing this state
     */
    public abstract void onApply();

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    public abstract String getMessage();

    /**
     * return the command line view that this state is applied to
     * @return the command line view that this state is applied to
     */
    public CommandLineView getView() {
        return view;
    }

    /**
     * Method used to write something on the stdout
     * @param s string containing what must be written
     */
    public void write(String s)
    {
        getView().write(s);
    }
}
