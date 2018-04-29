package progetto.clientintegration;

import javafx.application.Platform;
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

    @Test
    public void oneWindowTest (){

        new Thread(() -> ClientMain.main(null)).start();

        timoty.wait(2000);

        assertNotNull(ClientWindow.getWindow());

        Platform.runLater(()->ClientWindow.getWindow().closeWindow());

        timoty.wait(100);

        assertNull(ClientWindow.getWindow());

    }

}
