package progetto.commandline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCommandProcessor {

    CommandProcessor nuovo;
    String risposta;

    @Before
    public void setup(){

       nuovo = new CommandProcessor();

    }

    @Test
    public void testMissingCommand(){

        risposta=nuovo.processCommand("Ciao");
        assertEquals("Command not found, maybe you ment:", risposta);

    }

    @Test
    public void testEcho(){

        EchoCommand command = new EchoCommand();

        nuovo.registerCommand(command);
        risposta=nuovo.processCommand("echo Hello World");
        assertEquals("Hello", risposta);

    }

    @Test
    public void testEchoDuplicated (){

        Logger.getLogger(CommandProcessor.class.getName()).setLevel(Level.SEVERE);
        EchoCommand command = new EchoCommand();

        nuovo.registerCommand(command);

        try{

            command = new EchoCommand();
            nuovo.registerCommand(command);

        }
        catch(IllegalArgumentException e){

            assertTrue(true);

        }
    }

    @Test
    public void testDeRegistration(){

        EchoCommand command = new EchoCommand();

        nuovo.registerCommand(command);
        nuovo.deregisterCommand(command.getName());
        assertEquals(false,nuovo.existCommand(command));
        assertEquals(false,nuovo.existCommand(command.getName()));

        command = new EchoCommand();

        nuovo.registerCommand(command);
        nuovo.deregisterCommand(command);
        assertEquals(false,nuovo.existCommand(command));
        assertEquals(false,nuovo.existCommand(command.getName()));

    }

    @Test
    public void testHelp(){

        HelpCommand commhelp = new HelpCommand(nuovo);
        EchoCommand command = new EchoCommand();

        nuovo.registerCommand(commhelp);
        nuovo.registerCommand(command);
        risposta = nuovo.processCommand("help echo");

        assertEquals("Return the first argument received", risposta);

    }

    @Test
    public void testHelpHelp(){

        HelpCommand commhelp = new HelpCommand(nuovo);
        EchoCommand command = new EchoCommand();

        nuovo.registerCommand(commhelp);
        nuovo.registerCommand(command);
        risposta = nuovo.processCommand("help help");

        assertEquals("PANICKING!!!!!", risposta);
        risposta=commhelp.getName();

        assertEquals("help", risposta);

    }

    @Test
    public void testHelpCommandNotFound (){

        HelpCommand command = new HelpCommand(nuovo);

        nuovo.registerCommand(command);
        risposta = nuovo.processCommand("help ciao");

        assertEquals("No command no comment", risposta);

    }

    @Test
    public void testExistingCommand(){

        EchoCommand command = new EchoCommand();
        nuovo.registerCommand(command);

        assertTrue(nuovo.existCommand(command));

    }

    @Test
    public void testGetList(){

        List<ICommand> returned;
        List<ICommand> conteiner = new ArrayList<ICommand>();

        EchoCommand command = new EchoCommand();
        HelpCommand commhelp = new HelpCommand(nuovo);

        conteiner.add(command);
        conteiner.add(commhelp);

        nuovo.registerCommand(command);
        nuovo.registerCommand(commhelp);

        returned = nuovo.getList();

        assertTrue(returned.containsAll(conteiner));

    }

    @Test
    public void testMissingArgumentsHelp(){

        HelpCommand commhelp = new HelpCommand(nuovo);

        nuovo.registerCommand(commhelp);
        risposta = nuovo.processCommand("help");

        assertEquals("Missing arguments", risposta);

    }

    @Test
    public void testMissingArgumentsEcho(){

        EchoCommand echo = new EchoCommand();

        nuovo.registerCommand(echo);
        risposta = nuovo.processCommand("echo");

        assertEquals("Missing arguments", risposta);

    }


}
