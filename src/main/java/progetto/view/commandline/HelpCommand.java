package progetto.view.commandline;

/**
 * command that returns infos about what other commands does
 * @author Federica
 */
public class HelpCommand implements ICommand {

    private ICommandProcessor processor;

    /**
     * public constructor
     * @param processor the associated processor
     */
    public HelpCommand(ICommandProcessor processor){

        this.processor=processor;

    }

    /**
     *
     * @return the name of this command
     */
    public String getName() {
        return "help";
    }

    /**
     *
     * @return infos about what this command does
     */
    public String getHelp() {
        return "give help";
    }

    /**
     *
     * @param params recived message
     * @return if exist, help for the received command
     */
    public String execute(String[] params) {

        if(params==null){

            return "Missing arguments";
        }
        if(processor.existCommand(params[0])){
            return processor.getCommand(params[0]).getHelp();}
            else return "No command no comment";
        }

    }

