package progetto.view.commandline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.EndTurnAction;
import progetto.controller.FrameSetAction;
import progetto.controller.SetSeedAction;
import progetto.model.RoundState;
import progetto.model.ToolCardState;
import progetto.view.commandline.states.RoundViewState;
import progetto.view.commandline.states.UseToolCardState;

public class TestToolCardMultiPlayerSeed1 {

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
    public void testToolCardSelectRoundTrackDice(){
        commandLineView.execute("8");
        commandLineView.execute("8");
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();
        clientControllerStub.sendAction(new EndTurnAction(1));
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.execute("5");
        commandLineView.processAllPendings();
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(ToolCardState.class, clientControllerStub.getModel()
                .getMainBoard().getData().getGameState().getClass());
        Assert.assertEquals(UseToolCardState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("5 1");
        commandLineView.execute("5 0");
        commandLineView.execute("6");
        commandLineView.execute("6 a a v");
        commandLineView.execute("6 a bb");
        commandLineView.execute("6 2 2");
        commandLineView.execute("6 0 0");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getNDice()>=0);
        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getNDiceRT()>=0);

        commandLineView.execute("7");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }

    @After
    public void after(){commandLineView.stop();}
}
