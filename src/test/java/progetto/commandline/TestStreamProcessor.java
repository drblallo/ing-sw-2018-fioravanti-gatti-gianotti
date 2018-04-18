package progetto.commandline;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.junit.Assert.assertEquals;

public class TestStreamProcessor {

    CommandProcessor comproc;

    @Before
    public void setup()
    {
        comproc = new CommandProcessor();
    }


    @Test
    public void testEnter(){

        InputStreamStub in = new InputStreamStub("echo Hello World\n");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        EchoCommand echo = new EchoCommand();
        HelpCommand help = new HelpCommand(comproc);
        comproc.registerCommand(echo);
        comproc.registerCommand(help);

        stream.run();

        assertEquals("Hello\n", out.getString());

    }


    @Test
    public void testTab(){

        InputStreamStub in = new InputStreamStub("e\t");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        EchoCommand echo = new EchoCommand();
        HelpCommand help = new HelpCommand(comproc);
        comproc.registerCommand(echo);
        comproc.registerCommand(help);

        stream.run();

        assertEquals("echo\n", out.getString() );
    }

    @Test
    public void testNoCommand(){

        InputStreamStub in = new InputStreamStub("e\t");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        stream.run();

        assertEquals("", out.getString() );

    }

    @Test
    public void testNoCommandFound(){

        InputStreamStub in = new InputStreamStub("e\t");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        HelpCommand help = new HelpCommand(comproc);
        comproc.registerCommand(help);

        stream.run();

        assertEquals("", out.getString() );


    }

    @Test
    public void testIOException(){

        InputStreamStub in = new InputStreamStub(null);
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        stream.run();

        assertEquals(false, stream.isAlive());

    }

    @Test
    public void testTabNotAllowed(){

        InputStreamStub in = new InputStreamStub("echo H\t");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        EchoCommand echo = new EchoCommand();
        HelpCommand help = new HelpCommand(comproc);
        comproc.registerCommand(echo);
        comproc.registerCommand(help);

        stream.run();

        assertEquals("", out.getString() );

    }

}
