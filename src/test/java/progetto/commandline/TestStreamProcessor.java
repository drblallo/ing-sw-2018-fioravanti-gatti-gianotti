package progetto.commandline;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.junit.Assert.assertEquals;

public class TestStreamProcessor {

    CommandProcessor comproc;

    @Before
    public void setup() {
        comproc = new CommandProcessor("main");
    }


    @Test
    public void testEnterEndCommand() {

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
    public void testEnterPartialCommand() {

        InputStreamStub in = new InputStreamStub("e\n");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        EchoCommand echo = new EchoCommand();
        HelpCommand help = new HelpCommand(comproc);
        comproc.registerCommand(echo);
        comproc.registerCommand(help);

        stream.run();

        assertEquals("Command not found, maybe you ment:\necho\n", out.getString());
    }

    @Test
    public void testNoCommand() {

        InputStreamStub in = new InputStreamStub("e\n");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        stream.run();

        assertEquals("Command not found, maybe you ment:\n", out.getString());

    }

    @Test
    public void testNoCommandFound() {

        InputStreamStub in = new InputStreamStub("e\n");
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        HelpCommand help = new HelpCommand(comproc);
        comproc.registerCommand(help);

        stream.run();

        assertEquals("Command not found, maybe you ment:\n", out.getString());


    }

    @Test
    public void testIOException() {

        InputStreamStub in = new InputStreamStub(null);
        OutputStreamStub out = new OutputStreamStub();

        StreamProcessor stream = new StreamProcessor(new InputStreamReader(in), new OutputStreamWriter(out), comproc);

        stream.run();

        assertEquals(false, stream.isAlive());

    }



}
