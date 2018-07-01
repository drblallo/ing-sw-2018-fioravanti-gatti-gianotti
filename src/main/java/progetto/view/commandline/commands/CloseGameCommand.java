package progetto.view.commandline.commands;

import progetto.view.commandline.CommandLineView;

public class CloseGameCommand extends AbstractCLViewCommand {

    public CloseGameCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] args) {
        getController().shutDown();
    }

    @Override
    public String getHelp() {
        return "Spegni il gioco";
    }
}
