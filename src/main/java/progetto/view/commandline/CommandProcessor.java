package progetto.view.commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandProcessor implements ICommandProcessor
{

    private HashMap <String, ICommand> registered;
    private String name;
    private static final Logger LOGGER = Logger.getLogger(CommandProcessor.class.getName());

    public CommandProcessor(String name){

        this.name = name;
        registered = new HashMap<>();

    }

    public String getName() {
        return name;
    }

    public String getHelp() {
        return "This is a command processor, press enter to see all the available commands";
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

    public ICommand getCommand(String command){

        return registered.get(command);

    }

    public List<ICommand> getList(){

        return new ArrayList<>(registered.values());
    }

    public String execute(String params) {

        String[] tosplit;

        tosplit = params.split(" ",2);

        return execute(tosplit);
    }


    public String execute (String[] command){

        ICommand toexecute;
        StringBuilder toreturn = new StringBuilder();
        toreturn.append("Command not found, maybe you ment:");

        if(registered.containsKey(command[0])){

            toexecute = registered.get(command[0]);
            if(command.length!=1){

                command = command[1].split(" ");
                return toexecute.execute(command);
            }
            return toexecute.execute(null);
        }

        List<ICommand> explore = new ArrayList<>(registered.values());

        for(int i=0; i<explore.size();i++) {

            if (explore.get(i).getName().startsWith(command[0])) {

                toreturn.append('\n').append(explore.get(i).getName());

            }

        }

        return toreturn.toString();

        }


}
