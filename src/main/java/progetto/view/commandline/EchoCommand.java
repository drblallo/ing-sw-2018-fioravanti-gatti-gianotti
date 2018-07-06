package progetto.view.commandline;

/**
 * @author Federica
 */
public class EchoCommand implements ICommand {

    public String getName() {

        return "echo";

    }

    public String getHelp() {

        return "Return the first argument received";

    }

    public String execute(String[] params) {

        if(params==null){

            return "Missing arguments";

        }
        return params[0];

    }
}
