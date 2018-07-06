package progetto.view.commandline;

/**
 * Command that returns the first received word
 * @author Federica
 */
public class EchoCommand implements ICommand {

    /**
     *
     * @return the name of this command
     */
    public String getName() {
        return "echo";
    }

    /**
     *
     * @return infos about what this command does
     */
    public String getHelp() {
        return "Return the first argument received";
    }

    /**
     *
     * @param params recived message
     * @return the first word of the received message
     */
    public String execute(String[] params) {

        if(params==null){
            return "Missing arguments";
        }
        return params[0];

    }
}
