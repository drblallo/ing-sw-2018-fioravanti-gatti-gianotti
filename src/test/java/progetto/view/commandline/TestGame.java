package progetto.view.commandline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.ServerMain;
import progetto.controller.SetSeedAction;
import progetto.model.EndGameState;
import progetto.model.RoundState;
import progetto.model.ToolCardState;
import progetto.view.commandline.states.*;

public class TestGame {

    private CommandLineView commandLineView;
    private ClientControllerStub clientControllerStub;

    @Before
    public void setup(){

        clientControllerStub = new ClientControllerStub();
        commandLineView = new CommandLineView(clientControllerStub, System.out);

        clientControllerStub.sendAction(new SetSeedAction(1));
        commandLineView.execute("1");
        commandLineView.execute("2 good");
        commandLineView.execute("1 test");
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.execute("2 1");
        commandLineView.execute("3 5");
        commandLineView.execute("4");
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();
    }

    @Test
    public void testPickDiceCommand(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(PickDiceState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testPlaceDiceCommand(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("3 1");
        commandLineView.execute("3 ciao cc dd");
        commandLineView.execute("3 0 0 1");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel()
                .getPlayerBoard(0).getDicePlacedFrame().getData().getDice(0,1)!=null);
    }

    @Test
    public void testShowObjectives(){
        commandLineView.execute("6");
        commandLineView.processAllPendings();

        Assert.assertTrue(true);
    }

    @Test
    public void testShowRoundTrackDices(){
        commandLineView.execute("7");
        commandLineView.processAllPendings();
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.processAllPendings();
        commandLineView.execute("7");
        commandLineView.processAllPendings();
        Assert.assertTrue(true);

    }

    @Test
    public void testShowPlayerBoard(){

        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("0");
        commandLineView.processAllPendings();
        commandLineView.execute("3 0 0 0");
        commandLineView.execute("2");
        commandLineView.processAllPendings();

        Assert.assertEquals(ShowPlayerBoardState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testShowPickedDices(){

        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.execute("4");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testSetSinglePlayerDiceAction(){
        commandLineView.execute("8");
        commandLineView.processAllPendings();
        commandLineView.execute("5");
        commandLineView.execute("3");
        commandLineView.processAllPendings();

        commandLineView.execute("1");
        commandLineView.execute("1 ff");
        commandLineView.execute("1 0");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getSPDice()>=0);
        commandLineView.execute("4");
        commandLineView.execute("6");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testEndAGameAndStartANewOne(){
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.processAllPendings();

        Assert.assertEquals(EndGameState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(EndGameViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testCloseGame(){
        commandLineView.execute("10");
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("4");
        commandLineView.processAllPendings();

        Assert.assertTrue(true);
    }

    @Test
    public void testExitGameCommand(){

        commandLineView.execute("10");
        commandLineView.processAllPendings();

        Assert.assertEquals(ConfirmExitState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("2");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("10");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }


    @After
    public void after(){
        commandLineView.stop();
    }
}
