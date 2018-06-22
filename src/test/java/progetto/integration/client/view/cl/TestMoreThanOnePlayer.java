package progetto.integration.client.view.cl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.EndTurnAction;
import progetto.controller.FrameSetAction;
import progetto.controller.StartGameAction;
import progetto.model.FrameSelectionState;
import progetto.model.RoundState;

public class TestMoreThanOnePlayer {

    private CommandLineView commandLineView;
    private ClientControllerStub clientControllerStub;

    @Before
    public void setup(){

        clientControllerStub = new ClientControllerStub();
        commandLineView = new CommandLineView(clientControllerStub, null);

        commandLineView.execute("1");
        commandLineView.execute("2 good");
        commandLineView.execute("1 test");
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.execute("2 2");
        commandLineView.execute("3");
        commandLineView.execute("0");
        commandLineView.processAllPendings();
    }

    @Test
    public void testWaitingState(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(FrameSelectionViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(FrameSelectionState.class, clientControllerStub
                .getModel().getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(WaitingState.class, commandLineView.getAbstractCLViewState().getClass());

        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, clientControllerStub
                .getModel().getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testAnotherPlayerStartGame(){

        clientControllerStub.sendAction(new StartGameAction());
        commandLineView.processAllPendings();

        Assert.assertEquals(FrameSelectionState.class, clientControllerStub.getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(FrameSelectionViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }

    @Test
    public void testShowAnotherPlayerPlayerBoard(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }

    @Test
    public void testValidityFrameSelection(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new FrameSetAction(0,0,0));
        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, clientControllerStub.getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testWaitingTurnState(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();
        commandLineView.execute("8");
        commandLineView.processAllPendings();

        Assert.assertEquals(WaitingTurnState.class, commandLineView.getAbstractCLViewState().getClass());

        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testEndTurn(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();

        commandLineView.execute("8");
        commandLineView.processAllPendings();

        Assert.assertEquals(WaitingTurnState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @After
    public void after(){commandLineView.stop();}


}
