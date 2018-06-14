package progetto.integration.client.view.cl;

import progetto.integration.client.IClientController;
import progetto.model.IModel;
import progetto.view.commandline.ICommand;

public abstract class AbstractCLViewCommand implements ICommand{

    private CommandLineView commandLineView;

    public AbstractCLViewCommand(CommandLineView commandLineView){
        this.commandLineView = commandLineView;
    }

    public CommandLineView getCommandLineView() {
        return commandLineView;
    }

    public IClientController getController(){
        return commandLineView.getController();
    }

    public IModel getModel(){
        return commandLineView.getController().getModel();
    }

    public abstract void exec(String[] args);

    @Override
    public final String execute(String[] params) {
        exec(params);
        return "";
    }


    protected void setState(AbstractCLViewState state)
    {
        getCommandLineView().setState(state);
    }


    public void write(String s)
    {
        getCommandLineView().write(s);
    }
}
