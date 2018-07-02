package progetto.view.commandline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.view.commandline.states.*;
import progetto.model.FrameSelectionState;
import progetto.model.PreGameState;
import progetto.network.RoomView;
import progetto.utils.Waiter;

public class TestConnection {

    private CommandLineView commandLineView;
    private ClientControllerStub clientControllerStub;

    @Before
    public void setup(){
        clientControllerStub = new ClientControllerStub();
        commandLineView = new CommandLineView(clientControllerStub, System.out);
    }

    @Test
    public void testRun(){

        new Thread(commandLineView).start();

        Assert.assertEquals(true, commandLineView.getIsRunning());

        commandLineView.stop();

        Waiter waiter = new Waiter();
        waiter.wait(200);

        Assert.assertEquals(false, commandLineView.getIsRunning());

    }

    @Test
    public void testFirstMenuNewGame(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        Assert.assertEquals(SocketRMIChoiceState.class, commandLineView.getAbstractCLViewState().getClass());

    }

    @Test
    public void testChangeName(){

        commandLineView.execute("3");
        commandLineView.execute("3 Marco");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());
        Assert.assertEquals("Marco", commandLineView.getPlayerName());

    }

    @Test
    public void testCreateConnection() {

        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        Assert.assertEquals(SocketRMIChoiceState.class, commandLineView.getAbstractCLViewState().getClass());
        commandLineView.execute("1 localhost");
        commandLineView.processAllPendings();
        Assert.assertEquals(SocketRMIChoiceState.class, commandLineView.getAbstractCLViewState().getClass());
        commandLineView.execute("2 good");
        commandLineView.processAllPendings();
        Assert.assertEquals(RoomsState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testReturnFromSocketRMIChoiceState(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(SocketRMIChoiceState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("3");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }

    @Test
    public void testReturnFromRoomsState(){

        commandLineView.execute("1");
        commandLineView.execute("1 good");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoomsState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("4");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }

    @Test
    public void testRoomInfoChanged() {

        clientControllerStub.getRoomViewCallback().call(new RoomView("a", 1));
        clientControllerStub.getRoomViewCallback().call(new RoomView("b", 2));
        commandLineView.processAllPendings();

        Assert.assertNotNull(clientControllerStub);
    }

    /*@Test
    public void testPickChair(){

        commandLineView.execute("1");
        commandLineView.execute("1 good");
        commandLineView.execute("1 test");
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(PreGameState.class, clientControllerStub
                .getModel().getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(PreGameViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("3");
        commandLineView.processAllPendings();

        Assert.assertEquals(ChairSelectionState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(PreGameViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }

    @Test
    public void testNoPickChair(){

        commandLineView.execute("1");
        commandLineView.execute("1 good");
        commandLineView.execute("1 test");
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(FrameSelectionState.class, clientControllerStub.getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(FrameSelectionViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }



   /*@Test
    public void testGameStartAndContinue() {

        commandLineView.execute("1");
        commandLineView.execute("2 good");
        commandLineView.execute("1");
        commandLineView.execute("1 test");
        commandLineView.execute("3");
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.execute("2 7");
        commandLineView.execute("2 ciao");
        commandLineView.execute("2 1");
        commandLineView.execute("3");
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(FrameSelectionState.class, clientControllerStub
                .getModel().getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(FrameSelectionViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, clientControllerStub
                .getModel().getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("9");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("2");
        commandLineView.processAllPendings();

        Assert.assertEquals(ContinueGameState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("2");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, clientControllerStub.getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }*/


    @After
    public void after(){
        commandLineView.stop();
    }

}
