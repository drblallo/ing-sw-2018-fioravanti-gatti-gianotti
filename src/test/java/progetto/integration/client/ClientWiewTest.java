package progetto.integration.client;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import progetto.ClientMain;
import progetto.integration.server.ServerGameFactory;
import progetto.network.NetworkServer;
import progetto.network.localconnection.LocalConnectionModule;
import progetto.network.rmi.RMIModule;
import progetto.network.socket.SocketServer;
import progetto.utils.Waiter;

import static progetto.ServerMain.DEFAULT_PORT;

public class ClientWiewTest extends ApplicationTest {

    private NetworkServer networkServer;

    Waiter timoty = new Waiter();

    @Before
    public void setUp() throws Exception {

        networkServer = new NetworkServer(new ServerGameFactory());

        networkServer.addModules(new RMIModule());
        networkServer.addModules(new SocketServer(DEFAULT_PORT));
        networkServer.addModules(new LocalConnectionModule());
        networkServer.start();
        ApplicationTest.launch(ClientMain.class);

    }

    @Override
    public void start(Stage stage){

        stage.setMaximized(true);
        stage.show();

    }

    @Test
    public void testFirst() {

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
        clickOn("#commandLine");
        clickOn("#commandText");
        write("");
        clickOn("#sendButton");
        clickOn("#commandText");
        write("SetPlayerCountAction 0 1\n");
        write("StartGameAction 0\n");
        write("FrameSetAction 0 0 0\n");
        write("PickDiceAction 0 1\n");
        write("PlaceDiceAction 0 0 0 0\n");
        write("PlaceDiceAction 0 0 0 4\n");
        write("PlaceDiceAction 0 0 3 0\n");
        write("PlaceDiceAction 0 0 3 4\n");
        clickOn("#otherPlayers");
        timoty.wait(1000);
        clickOn("#commandLine");
        clickOn("#commandText");
        write("EndTurnAction 0\n");
        write("EndTurnAction 0\n");
        write("EndTurnAction 0\n");
        clickOn("#roundTrack");
        moveTo("#round11");
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


        timoty.wait(500);
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

    @Test
    public void testTwoGame(){

        clickOn("#newGame");
        clickOn("#connectButton");
        clickOn("#roomNameTextField");
        write("Room1");
        clickOn("#createRoom");
        clickOn("#usernameTextField");
        write("Luca");
        clickOn("#listView");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("#enterButton");
        clickOn("#backButton");

        clickOn("#newGame");
        clickOn("#connectButton");
        clickOn("#roomNameTextField");
        write("Room2");
        clickOn("#createRoom");
        clickOn("#usernameTextField");
        write("Luca");
        clickOn("#listView");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("#enterButton");

        Assert.assertTrue(true);
    }

    @After
    public void tearDown() throws Exception {

        timoty.wait(500);

        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
        networkServer.stop();

        timoty.wait(500);
    }

}
