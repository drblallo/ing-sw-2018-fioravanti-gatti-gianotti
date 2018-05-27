package progetto.clientintegration;

import javafx.application.Platform;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.utils.Waiter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

public class ClientWindowTest {

    Waiter timoty;


    @Before
    public void setUp(){

        timoty = new Waiter();

    }

    /*@Test
    public void testOneClientWindow(){

        new Thread(() -> ClientMain.main(null)).start();

        int a = 0;
        while (a++ < 10 && ClientWindow.getWindow() == null)
			timoty.wait(500);

        ClientCommandProcessor.getCommandProcessor();
        assertNotNull(ClientWindow.getWindow());

        Platform.runLater(()->ClientWindow.getWindow().closeWindow());


    } */

}
