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

public class TestToolCardMultiPlayerSeed4 {
    private CommandLineView commandLineView;
    private ClientControllerStub clientControllerStub;

    @Before
    public void setup(){

        clientControllerStub = new ClientControllerStub();
        commandLineView = new CommandLineView(clientControllerStub, System.out);

        clientControllerStub.sendAction(new SetSeedAction(4));
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
    public void testToolCardSetDiceValue(){
        commandLineView.execute("1");
        commandLineView.execute("1");
        commandLineView.processAllPendings();
        commandLineView.execute("5");
        commandLineView.processAllPendings();
        commandLineView.execute("2");
        commandLineView.processAllPendings();
        commandLineView.execute("6");
        commandLineView.execute("5 0");
        commandLineView.execute("\n");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getNDice() == 0);

        commandLineView.execute("6");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundState.class, commandLineView.getController().getModel().getMainBoard()
                .getData().getGameState().getClass());
        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("1");
        commandLineView.execute("1 aa");
        commandLineView.execute("1 7");
        commandLineView.execute("1 6");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel().getRoundInformation().getData()
                .getToolCardParameters().getValue() == 6);
    }

    @After
    public void after(){commandLineView.stop();}
}
