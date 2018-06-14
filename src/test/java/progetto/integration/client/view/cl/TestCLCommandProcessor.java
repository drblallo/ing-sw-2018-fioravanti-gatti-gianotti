package progetto.integration.client.view.cl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.view.commandline.AbstractCommandProcessorTest;

public class TestCLCommandProcessor extends AbstractCommandProcessorTest {

    @Before
    public void setup(){
        CLCommandProcessor clCommandProcessor = new CLCommandProcessor();
        clCommandProcessor.setCurrentState(new StateStub("Stub", new CommandLineView(new ClientControllerStub(), null)));
        nuovo = clCommandProcessor;
    }

    @Test
    public void testGetName(){
        nuovo.execute("Hi");
        Assert.assertEquals("Stub", nuovo.getName());
    }

}
