package progetto.commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandProcessor {

    private HashMap <String, ICommand> registered;
    private static final Logger LOGGER = Logger.getLogger(CommandProcessor.class.getName());

    public CommandProcessor(){

        registered = new HashMap<String, ICommand>();

    }

    public void registerCommand(ICommand command){

        if(registered.containsKey(command.getName()))
        {
            LOGGER.log(Level.WARNING,"Command with the same name is already registered {0}", command.getName());
            throw new IllegalArgumentException("Command with the same name is already registered");
        }
        registered.put(command.getName(), command);

    }

    public void deregisterCommand(ICommand command){

        registered.remove(command.getName());

    }

    public void deregisterCommand(String name) {

        registered.remove(name);

    }


    public boolean existCommand(String name) {

        return registered.containsKey(name);

    }

    public boolean existCommand(ICommand command){

        return registered.containsKey(command.getName());
    }

    ICommand getCommand(String command){

        return registered.get(command);

    }

    public List<ICommand> getList(){

        return new ArrayList<ICommand>(registered.values());
    }

    public String processCommand(String command){

        ICommand toexecute;
        String[] tosplit;

        tosplit = command.split(" ",2);

        if(registered.containsKey(tosplit[0])){

            toexecute = registered.get(tosplit[0]);
            tosplit = tosplit[1].split(" ");
            return toexecute.execute(tosplit);
        }
        else return "Command not found";
    }
}
