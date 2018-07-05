package progetto.view.commandline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.FrameSetAction;
import progetto.controller.SetSeedAction;
import progetto.model.RoundState;
import progetto.model.ToolCardState;
import progetto.view.commandline.states.RoundViewState;
import progetto.view.commandline.states.UseToolCardState;

public class TestToolCardsMultiPlayerSeed0 {

    private CommandLineView commandLineView;
    private ClientControllerStub clientControllerStub;

    @Before
    public void setup(){

        clientControllerStub = new ClientControllerStub();
        commandLineView = new CommandLineView(clientControllerStub, System.out);

        commandLineView.execute("1");
        commandLineView.execute("2 good");
        commandLineView.execute("1 test");
        commandLineView.execute("2");
        commandLineView.execute("1");
        commandLineView.execute("2 2");
        commandLineView.execute("3");
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();
    }

    @Test
    public void testActivateAndDisactivateToolCard(){
        commandLineView.execute("5");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(ToolCardState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(UseToolCardState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("7");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @Test
    public void testToolCardSelectPlacedDice(){
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.execute("3 0 0 0");
        commandLineView.execute("5");
        commandLineView.processAllPendings();
        commandLineView.execute("2");
        commandLineView.processAllPendings();
        commandLineView.execute("5");
        commandLineView.execute("5 4 2 3");
        commandLineView.execute("5 9 9");
        commandLineView.execute("5 0 0");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getXPlacedDice() == 0);
        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getYPlacedDice() == 0);

        commandLineView.execute("6");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }
    @Test
    public void testToolCardSelectPickedDice(){
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.execute("5");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        Assert.assertEquals(ToolCardState.class, clientControllerStub.getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(UseToolCardState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("5 0");
        commandLineView.processAllPendings();

        Assert.assertEquals(0, commandLineView.getController().getModel().getRoundInformation()
                .getData().getToolCardParameters().getNDice());

        commandLineView.execute("6");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard().getData()
                .getGameState().getClass());
    }

    @After
    public void after(){commandLineView.stop();}
}
