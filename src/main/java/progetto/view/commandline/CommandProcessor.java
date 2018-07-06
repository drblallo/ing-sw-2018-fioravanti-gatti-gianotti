package progetto.view.commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implement iCommandProcessor
 * @author Federica
 */
public class CommandProcessor implements ICommandProcessor
{
    private HashMap <String, ICommand> registered;
    private String name;
    private static final Logger LOGGER = Logger.getLogger(CommandProcessor.class.getName());

    /**
     * public constructor
     * @param name name of this processor
     */
    public CommandProcessor(String name){

        this.name = name;
        registered = new HashMap<>();

    }

    /**
     *
     * @return number of contained commands
     */
    public int getCommandCount()
    {
        return registered.values().size();
    }

    /**
     *
     * @return name of this processor
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return infos about what this object is and what it does
     */
    public String getHelp() {
        return "This is a command processor, press enter to see all the available commands";
    }

    /**
     *
     * @param command to be registered
     */
    public void registerCommand(ICommand command){

        if(registered.containsKey(command.getName()))
        {
            LOGGER.log(Level.WARNING,"Command with the same name is already registered {0}", command.getName());
            throw new IllegalArgumentException("Command with the same name is already registered");
        }
        registered.put(command.getName(), command);

    }

    /**
     *
     * @param command to be unregistered
     */
    public void deregisterCommand(ICommand command){

        registered.remove(command.getName());

    }

    /**
     *
     * @param name name of the command to be unregistered
     */
    public void deregisterCommand(String name) {
        registered.remove(name);
    }

    /**
     *
     * @param name name of the command to check
     * @return true if the command is contained
     */
    public boolean existCommand(String name) {
        return registered.containsKey(name);
    }

    /**
     *
     * @param command command to check
     * @return true if the command is contained
     */
    public boolean existCommand(ICommand command){
        return registered.containsKey(command.getName());
    }

    /**
     *
     * @param command name of the required command
     * @return required command
     */
    public ICommand getCommand(String command){

        return registered.get(command);

    }

    /**
     *
     * @return a list of commands
     */
    public List<ICommand> getList(){
        List<ICommand> toBeReturned = new ArrayList<>(registered.values());
        toBeReturned.sort((o1, o2) -> {
            if (o1.getName().length()!=o2.getName().length())
                return o1.getName().length() - o2.getName().length();
            else return o1.getName().compareTo(o2.getName()); });
        return toBeReturned;
    }

    /**
     *
     * @param params input
     * @return if necessary, return the string that must be sent the the user
     */
    public String execute(String params) {

        String[] tosplit;

        tosplit = params.split(" ",2);

        return execute(tosplit);
    }

    /**
     *
     * @param command
     * @return if necessary, return the string that must be sent the the user
     */
    public String execute (String[] command){

        ICommand toexecute;
        StringBuilder toreturn = new StringBuilder();
        toreturn.append("Il comando selezionato non esiste, inserire un comando valido tra quelli proposti:\n");

        if(registered.containsKey(command[0])){

            toexecute = registered.get(command[0]);
            if(command.length!=1){

                command = command[1].split(" ");
                return toexecute.execute(command);
            }
            return toexecute.execute(null);
        }

        toreturn.append(getContent());
        return toreturn.toString();

        }

    /**
     *
     * @return a string with all the name of the commands contained by this command processor
     */
    @Override
    public String getContent() {

        List<ICommand> explore = getList();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<explore.size();i++) {

            stringBuilder.append('\n').append(explore.get(i).getName()).append(" - ").append(explore.get(i).getHelp());
        }

        return stringBuilder.toString();

    }
}
