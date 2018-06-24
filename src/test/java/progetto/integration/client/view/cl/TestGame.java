package progetto.integration.client.view.cl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGame {

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
        commandLineView.execute("2 1");
        commandLineView.execute("3");
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
        commandLineView.execute("0");
        commandLineView.processAllPendings();
        commandLineView.execute("3 1");
        commandLineView.execute("3 ciao cc dd");
        commandLineView.execute("3 0 0 0");
        commandLineView.processAllPendings();

        Assert.assertTrue(commandLineView.getController().getModel()
                .getPlayerBoard(0).getDicePlacedFrame().getData().getDice(0,0)!=null);
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
        commandLineView.execute("0");
        commandLineView.execute("4");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());
    }


    @Test
    public void testExitGameCommand(){

        commandLineView.execute("9");
        commandLineView.processAllPendings();

        Assert.assertEquals(ConfirmExitState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("2");
        commandLineView.processAllPendings();

        Assert.assertEquals(RoundViewState.class, commandLineView.getAbstractCLViewState().getClass());

        commandLineView.execute("9");
        commandLineView.execute("1");
        commandLineView.processAllPendings();

        Assert.assertEquals(DefaultViewState.class, commandLineView.getAbstractCLViewState().getClass());

    }


    @After
    public void after(){
        commandLineView.stop();
    }

}
