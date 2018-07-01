package progetto.view.commandline.states;

import progetto.IClientController;
import progetto.model.IModel;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.CommandProcessor;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.AbstractCLViewCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCLViewState extends CommandProcessor
{

    private final CommandLineView view;
    private final List<DifferenceDescriptor> differenceDescriptors = new ArrayList<>();

    public AbstractCLViewState(String name, CommandLineView cl)
    {
        super(name);
        view = cl;
    }

    public void registerCommand(AbstractCLViewCommand command)
    {
       command.setName(getCommandCount() + 1);
       super.registerCommand(command);
    }

    public IClientController getController(){
        return view.getController();
    }

    public IModel getModel(){
        return view.getController().getModel();
    }

    public boolean isStillValid(){
        return true;
    }

    public void addObservers(){
        //
    }

    public void reloadObservers()
    {
        for (DifferenceDescriptor d: differenceDescriptors)
            d.detach();

        differenceDescriptors.clear();
        addObservers();
    }

    public final void onRemove()
    {
        for (DifferenceDescriptor d: differenceDescriptors)
            d.detach();

        differenceDescriptors.clear();
    }

    protected List<DifferenceDescriptor> getDifferenceDescriptors()
    {
        return differenceDescriptors;
    }

    public abstract void onApply();

    public abstract String getMessage();

    public CommandLineView getView() {
        return view;
    }

    public void write(String s)
    {
        getView().write(s);
    }
}
