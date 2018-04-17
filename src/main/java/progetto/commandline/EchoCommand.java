package progetto.commandline;

public class EchoCommand implements ICommand {

    public String getName() {

        return "echo";

    }

    public String getHelp() {

        return "Return the first argument received";

    }

    public String execute(String[] params) {

        return params[0];

    }
}
