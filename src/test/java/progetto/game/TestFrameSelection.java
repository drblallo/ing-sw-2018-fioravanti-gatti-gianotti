package progetto.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFrameSelection {

	Game game;

	@Before
	public void setUp()
	{
		game = new Game();
	}

	@Test
	public void testPublicObjectiveCard4Player()
	{
		game.setState(new FrameSelectionState());
		Assert.assertEquals(4, game.getMainBoard().getData().getPlayerCount());
		AbstractPublicObjectiveCard[] publicObjectiveCard = game.getMainBoard().getData().getPublicObjectiveCards();
		Assert.assertEquals(3, publicObjectiveCard.length);
		Assert.assertEquals("Sfumature Medie Set di 3 & 4 ovunque", publicObjectiveCard[0].getToolTip());
		Assert.assertEquals("Sfumature diverse - riga Righe senza sfumature ripetute", publicObjectiveCard[1].getToolTip());
		Assert.assertEquals("Sfumature Scure Set di 4 & 5 ovunque", publicObjectiveCard[2].getToolTip());

	}

	@Test
	public void testPublicObjectiveCard1Player()
	{
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());
		Assert.assertEquals(1, game.getMainBoard().getData().getPlayerCount());
		AbstractPublicObjectiveCard[] publicObjectiveCard = game.getMainBoard().getData().getPublicObjectiveCards();
		Assert.assertEquals(2, publicObjectiveCard.length);
		Assert.assertEquals("Diagonali Colorate Numero di dadi dello stesso colore diagonalmente adiacenti", publicObjectiveCard[0].getToolTip());
		Assert.assertEquals("Sfumature diverse - riga Righe senza sfumature ripetute", publicObjectiveCard[1].getToolTip());

	}

	@Test
	public void testPrivateObjectiveCard4Player()
	{
		game.setState(new FrameSelectionState());
		Assert.assertEquals(4, game.getMainBoard().getData().getPlayerCount());
		AbstractPrivateObjectiveCard[] privateObjectiveCardP0 = game.getPlayerBoard(0).getData().getPrivateObjectiveCard();
		AbstractPrivateObjectiveCard[] privateObjectiveCardP1 = game.getPlayerBoard(1).getData().getPrivateObjectiveCard();
		AbstractPrivateObjectiveCard[] privateObjectiveCardP2 = game.getPlayerBoard(2).getData().getPrivateObjectiveCard();
		AbstractPrivateObjectiveCard[] privateObjectiveCardP3 = game.getPlayerBoard(3).getData().getPrivateObjectiveCard();
		Assert.assertEquals(1, privateObjectiveCardP0.length);
		Assert.assertEquals(1, privateObjectiveCardP1.length);
		Assert.assertEquals(1, privateObjectiveCardP2.length);
		Assert.assertEquals(1, privateObjectiveCardP3.length);
		Assert.assertEquals("Sfumature Giallo Somma dei valori su tutti i dadi Giallo", privateObjectiveCardP0[0].getToolTip());
		Assert.assertEquals("Sfumature Viola Somma dei valori su tutti i dadi Viola", privateObjectiveCardP1[0].getToolTip());
		Assert.assertEquals("Sfumature Blu Somma dei valori su tutti i dadi Blu", privateObjectiveCardP2[0].getToolTip());
		Assert.assertEquals("Sfumature Verde Somma dei valori su tutti i dadi Verde", privateObjectiveCardP3[0].getToolTip());

	}

	@Test
	public void testPrivateObjectiveCard1Player()
	{
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());
		Assert.assertEquals(1, game.getMainBoard().getData().getPlayerCount());
		AbstractPrivateObjectiveCard[] privateObjectiveCardP0 = game.getPlayerBoard(0).getData().getPrivateObjectiveCard();
		Assert.assertEquals(2, privateObjectiveCardP0.length);
		Assert.assertEquals("Sfumature Giallo Somma dei valori su tutti i dadi Giallo", privateObjectiveCardP0[0].getToolTip());
		Assert.assertEquals("Sfumature Viola Somma dei valori su tutti i dadi Viola", privateObjectiveCardP0[1].getToolTip());

	}

	@Test
	public void testFrameExtraction1Player()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();
		for(int i=0; i<windowFrameCoupleArray.getWindowFrameCouples().size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCoupleArray.getWindowFrameCouples().get(i)));
		}
		game.processAllPendingAction();
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());

		Assert.assertEquals(1, game.getMainBoard().getData().getPlayerCount());
		Assert.assertFalse(game.getPlayerBoard(0).getData().getWindowFrameIsSet());
		Assert.assertEquals(2, game.getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame().length);

		WindowFrameCouple[] windowFrameCouples = game.getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame();

		Assert.assertEquals("Kaleidoscopic Dream", windowFrameCouples[0].getWindowFrame(0).getName());
		Assert.assertEquals("Firmitas", windowFrameCouples[0].getWindowFrame(1).getName());
		Assert.assertEquals("Fractal Drops", windowFrameCouples[1].getWindowFrame(0).getName());
		Assert.assertEquals("Ripples of Light", windowFrameCouples[1].getWindowFrame(1).getName());

	}

	@Test
	public void testFrameExtraction4Player()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();
		for(int i=0; i<windowFrameCoupleArray.getWindowFrameCouples().size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCoupleArray.getWindowFrameCouples().get(i)));
		}
		game.processAllPendingAction();
		game.setState(new FrameSelectionState());

		Assert.assertEquals(4, game.getMainBoard().getData().getPlayerCount());

		Assert.assertFalse(game.getPlayerBoard(0).getData().getWindowFrameIsSet());
		Assert.assertFalse(game.getPlayerBoard(1).getData().getWindowFrameIsSet());
		Assert.assertFalse(game.getPlayerBoard(2).getData().getWindowFrameIsSet());
		Assert.assertFalse(game.getPlayerBoard(3).getData().getWindowFrameIsSet());

		Assert.assertEquals(2, game.getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame().length);

		WindowFrameCouple[] windowFrameCouples = game.getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame();

		Assert.assertEquals("Chromatic Splendor", windowFrameCouples[0].getWindowFrame(0).getName());
		Assert.assertEquals("Comitas", windowFrameCouples[0].getWindowFrame(1).getName());
		Assert.assertEquals("Fractal Drops", windowFrameCouples[1].getWindowFrame(0).getName());
		Assert.assertEquals("Ripples of Light", windowFrameCouples[1].getWindowFrame(1).getName());

		Assert.assertEquals(2, game.getPlayerBoard(3).getData().getExtractedWindowFrameCouplesWindowFrame().length);

		windowFrameCouples = game.getPlayerBoard(3).getData().getExtractedWindowFrameCouplesWindowFrame();

		Assert.assertEquals("Via Lux", windowFrameCouples[0].getWindowFrame(0).getName());
		Assert.assertEquals("Industria", windowFrameCouples[0].getWindowFrame(1).getName());
		Assert.assertEquals("Virtus", windowFrameCouples[1].getWindowFrame(0).getName());
		Assert.assertEquals("Symphony of Light", windowFrameCouples[1].getWindowFrame(1).getName());

	}

	@Test
	public void testFrameSelection1Player()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();
		for(int i=0; i<windowFrameCoupleArray.getWindowFrameCouples().size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCoupleArray.getWindowFrameCouples().get(i)));
		}
		game.processAllPendingAction();
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, 1, 0));
		game.processAction();
		Assert.assertEquals("Fractal Drops", game.getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Round State", game.getMainBoard().getData().getGameState().getName());

	}

	@Test
	public void testFrameSelection4Player()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();
		for(int i=0; i<windowFrameCoupleArray.getWindowFrameCouples().size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCoupleArray.getWindowFrameCouples().get(i)));
		}
		game.processAllPendingAction();
		game.setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, 0, 0));
		game.processAction();

		game.sendAction(new FrameSetAction(1, 0, 1));
		game.processAction();

		game.sendAction(new FrameSetAction(2, 1, 0));
		game.processAction();

		game.sendAction(new FrameSetAction(3, 1, 1));
		game.processAction();

		Assert.assertEquals("Chromatic Splendor", game.getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Aurora Sagradis", game.getPlayerBoard(1).getData().getWindowFrame().getName());
		Assert.assertEquals("Water of Life", game.getPlayerBoard(2).getData().getWindowFrame().getName());
		Assert.assertEquals("Symphony of Light", game.getPlayerBoard(3).getData().getWindowFrame().getName());

		Assert.assertEquals("Round State", game.getMainBoard().getData().getGameState().getName());

	}

	@Test
	public void testFrameSelection1PlayerEmptyWindowFrame()
	{
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, -1, 0));
		game.processAction();

		Assert.assertEquals("", game.getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Round State", game.getMainBoard().getData().getGameState().getName());

	}

	@Test
	public void testFrameSelection1PlayerEmptyError()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();
		for(int i=0; i<windowFrameCoupleArray.getWindowFrameCouples().size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCoupleArray.getWindowFrameCouples().get(i)));
		}
		game.processAllPendingAction();
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, 2, 0));
		game.processAction();

		Assert.assertEquals("", game.getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Frame selection", game.getMainBoard().getData().getGameState().getName());

		game.sendAction(new FrameSetAction(0, 1, 0));
		game.processAction();
		Assert.assertEquals("Fractal Drops", game.getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Round State", game.getMainBoard().getData().getGameState().getName());


	}

}
