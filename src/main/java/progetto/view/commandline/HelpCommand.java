package progetto.view.commandline;

public class HelpCommand implements ICommand {

    private ICommandProcessor processor;

    public HelpCommand(ICommandProcessor processor){

        this.processor=processor;

    }

    public String getName() {
        return "help";
    }

    public String getHelp() {
        return "PANICKING!!!!!";
    }

    public String execute(String[] params) {

        if(params==null){

            return "Missing arguments";
        }
        if(processor.existCommand(params[0])){
            return processor.getCommand(params[0]).getHelp();}
            else return "No command no comment";
        }

    }

