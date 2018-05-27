package progetto.clientintegration;

import javafx.application.Platform;
import javafx.geometry.VerticalDirection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import progetto.network.NetworkServer;
import progetto.network.localconnection.LocalConnectionModule;
import progetto.network.rmi.RMIModule;
import progetto.network.socket.SocketServer;
import progetto.serverintegration.ServerGameFactory;
import progetto.serverintegration.ServerMain;
import progetto.utils.Waiter;

import static progetto.serverintegration.ServerMain.DEFAULT_PORT;

public class ClientWiewTest extends ApplicationTest {

    private NetworkServer networkServer;

    @Before
    public void setUp() throws Exception {

        networkServer = new NetworkServer(new ServerGameFactory());

        networkServer.addModules(new RMIModule());
        networkServer.addModules(new SocketServer(DEFAULT_PORT));
        networkServer.addModules(new LocalConnectionModule());
        networkServer.start();
        ApplicationTest.launch(ClientWindow.class);

    }

    @Override
    public void start(Stage stage){

        stage.setMaximized(true);
        stage.show();

    }

    @Test
    public void testFirst() {

        Waiter timoty = new Waiter();

        clickOn("#newGame");
        clickOn("#rmi");
        clickOn("#rmi");
        clickOn("#socket");
        clickOn("#socket");
        clickOn("#connectButton");
        clickOn("#createRoom");
        clickOn("#roomNameTextField");
        write("Room1");
        clickOn("#createRoom");
        clickOn("#enterButton");
        clickOn("#usernameTextField");
        write("Luca");
        clickOn("#enterButton");
        clickOn("#update");
        clickOn("#listView");
        type(KeyCode.DOWN);
        clickOn("#enterButton");
        type(KeyCode.DOWN);
        clickOn("#listView");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("#enterButton");
        clickOn("#chatPane");
        clickOn("#chairs");
        clickOn("#sitButton");
        clickOn("#chairs");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#sitButton");
        clickOn("#messageToSend");
        write("Hi\n");
        clickOn("#commandLinePane");
        clickOn("#commandText");
        write("");
        clickOn("#sendButton");
        clickOn("#commandText");
        write("SetPlayerCountAction 0 2\n");
        write("StartGameAction 0\n");
        write("FrameSetAction 0 -1\n");
        write("PickDiceAction 0 1\n");
        clickOn("#mainTab");
        timoty.wait(1000);
        clickOn("#commandLinePane");
        clickOn("#commandText");
        write("EndTurnAction 0\n");
        write("EndTurnAction 0\n");
        write("EndTurnAction 0\n");
        clickOn("#mainTab");
        timoty.wait(500);
        clickOn("#backButton");
        clickOn("#continueButton");
        clickOn("#backButton");
        clickOn("#continueButton");
        clickOn("#selectButton");
        clickOn("#listView");
        type(KeyCode.DOWN);
        clickOn("#selectButton");
        clickOn("#backButton");
        clickOn("#newGame");
        clickOn("#rmi");
        clickOn("#connectButton");

        Assert.assertTrue(true);

    }

    @Test
    public void testNoClientConnection(){

        clickOn("#newGame");
        clickOn("#iPAddress");
        write("0.0.0.0");
        clickOn("#rmi");
        clickOn("#connectButton");

        Assert.assertTrue(true);
    }

    @After
    public void tearDown() throws Exception {

        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});

        networkServer.stop();

    }
}
