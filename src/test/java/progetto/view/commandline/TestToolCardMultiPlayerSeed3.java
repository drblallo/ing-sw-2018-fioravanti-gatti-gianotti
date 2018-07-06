package progetto.view.commandline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.EndTurnAction;
import progetto.controller.FrameSetAction;
import progetto.controller.SetSeedAction;
import progetto.model.RoundState;
import progetto.view.commandline.states.RoundViewState;
import progetto.view.commandline.states.ShowToolCardState;

public class TestToolCardMultiPlayerSeed3 {


    private CommandLineView commandLineView;
    private ClientControllerStub clientControllerStub;

    @Before
    public void setup(){

        clientControllerStub = new ClientControllerStub();
        commandLineView = new CommandLineView(clientControllerStub, System.out);

        clientControllerStub.sendAction(new SetSeedAction(3));
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
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new FrameSetAction(1,0,0));
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();
    }

    @Test
    public void testToolCardSetSecondPlacedDice(){
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.execute("3 0 0 0"); // BLU
        commandLineView.execute("8");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.execute("2");
        commandLineView.execute("3 0 1 0");
        commandLineView.execute("8");
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.execute("3 0 2 0"); //BLU
        commandLineView.execute("8");
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new EndTurnAction(1));
        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();
        commandLineView.execute("5");
        commandLineView.processAllPendings();
        commandLineView.execute("2");
        commandLineView.processAllPendings();
        commandLineView.execute("5 0 1");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();
        commandLineView.execute("6 aa bb");
        commandLineView.execute("6 0 0");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();
        commandLineView.execute("7 7 7");
        commandLineView.execute("7 0 2");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();

        Assert.assertEquals(2, commandLineView.getController().getModel().getRoundInformation()
                .getData().getToolCardParameters().getXPlacedDice2());

        commandLineView.execute("8");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }
    @Test
    public void testUseOfToolCardNotPermitted(){
        commandLineView.execute("5");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard().getData()
                .getGameState().getClass());
        Assert.assertEquals(ShowToolCardState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @After
    public void after(){commandLineView.stop();}

}
