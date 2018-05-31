package progetto.view.commandline;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class TestMultipleProcessorCommand {

    CommandProcessor main;
    CommandProcessor son;

    @Before
    public void setup(){

        main = new CommandProcessor("main");
        son = new CommandProcessor("son");
        main.registerCommand(son);
    }

    @Test
    public void testGetList(){

        EchoCommand echo = new EchoCommand();
        HelpCommand help = new HelpCommand(main);

        List<ICommand> contenitore = new ArrayList<ICommand>();

        contenitore.add(son);

        main.registerCommand(echo);
        contenitore.add(echo);

        String frommain;
        frommain = main.getList().toString();
        assertTrue(main.getList().containsAll(contenitore));
        assertTrue(contenitore.containsAll(main.getList()));

        son.registerCommand(help);

        contenitore.clear();
        contenitore.add(help);

        son.registerCommand(echo);

        contenitore.add(echo);

        String fromson;
        fromson  = son.getList().toString();

        assertTrue(son.getList().containsAll(contenitore));
        assertTrue(contenitore.containsAll(son.getList()));

    }

}
