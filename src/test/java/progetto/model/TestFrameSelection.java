package progetto.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.controller.FrameSetAction;
import progetto.controller.GameController;

import java.util.List;

/**
 * Test frame selection state
 */
public class TestFrameSelection {

	GameController game;

	@Before
	public void setUp()
	{
		game = new GameController();
	}

	/**
	 * Test to verify extraction of public objective cards (MultiPlayer)
	 */
	@Test
	public void testPublicObjectiveCard4Player()
	{
		Model game = this.game.getModel();
		game.setState(new FrameSelectionState());
		Assert.assertEquals(4, game.getMainBoard().getData().getPlayerCount());
		List<AbstractPublicObjectiveCard> cards = game.getMainBoard().getData().getPublicObjectiveCards();
		Assert.assertEquals(3, cards.size());
		Assert.assertEquals("Sfumature Medie Set di 3 & 4 ovunque", cards.get(0).getToolTip());
		Assert.assertEquals("Sfumature diverse - riga Righe senza sfumature ripetute", cards.get(1).getToolTip());
		Assert.assertEquals("Sfumature Scure Set di 4 & 5 ovunque", cards.get(2).getToolTip());

	}

	/**
	 * Test to verify extraction of public objective cards (Single Player)
	 */
	@Test
	public void testPublicObjectiveCard1Player()
	{
		Model game = this.game.getModel();
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());
		Assert.assertEquals(1, game.getMainBoard().getData().getPlayerCount());
		List<AbstractPublicObjectiveCard> cards = game.getMainBoard().getData().getPublicObjectiveCards();
		Assert.assertEquals(2, cards.size());
		Assert.assertEquals("Diagonali Colorate Numero di dadi dello stesso colore diagonalmente adiacenti", cards.get(0).getToolTip());
		Assert.assertEquals("Sfumature diverse - riga Righe senza sfumature ripetute", cards.get(1).getToolTip());

	}

	/**
	 * Test to verify extraction of private objective cards (MultiPlayer)
	 */
	@Test
	public void testPrivateObjectiveCard4Player()
	{
		Model game = this.game.getModel();
		game.setState(new FrameSelectionState());
		Assert.assertEquals(4, game.getMainBoard().getData().getPlayerCount());
		List<AbstractPrivateObjectiveCard> privateObjectiveCardP0 = game.getPlayerBoard(0).getData().getPrivateObjectiveCard();
		List<AbstractPrivateObjectiveCard> privateObjectiveCardP1 = game.getPlayerBoard(1).getData().getPrivateObjectiveCard();
		List<AbstractPrivateObjectiveCard> privateObjectiveCardP2 = game.getPlayerBoard(2).getData().getPrivateObjectiveCard();
		List<AbstractPrivateObjectiveCard> privateObjectiveCardP3 = game.getPlayerBoard(3).getData().getPrivateObjectiveCard();
		Assert.assertEquals(1, privateObjectiveCardP0.size());
		Assert.assertEquals(1, privateObjectiveCardP1.size());
		Assert.assertEquals(1, privateObjectiveCardP2.size());
		Assert.assertEquals(1, privateObjectiveCardP3.size());
		Assert.assertEquals("Sfumature Giallo Somma dei valori su tutti i dadi Giallo", privateObjectiveCardP0.get(0).getToolTip());
		Assert.assertEquals("Sfumature Viola Somma dei valori su tutti i dadi Viola", privateObjectiveCardP1.get(0).getToolTip());
		Assert.assertEquals("Sfumature Blu Somma dei valori su tutti i dadi Blu", privateObjectiveCardP2.get(0).getToolTip());
		Assert.assertEquals("Sfumature Verde Somma dei valori su tutti i dadi Verde", privateObjectiveCardP3.get(0).getToolTip());

	}

	/**
	 * Test to verify extraction of private objective cards (single player)
	 */
	@Test
	public void testPrivateObjectiveCard1Player()
	{
		Model game = this.game.getModel();
		game.getMainBoard().setPlayerCount(1);
		game.setState(new FrameSelectionState());
		Assert.assertEquals(1, game.getMainBoard().getData().getPlayerCount());
		List<AbstractPrivateObjectiveCard> privateObjectiveCardP0 = game.getPlayerBoard(0).getData().getPrivateObjectiveCard();
		Assert.assertEquals(2, privateObjectiveCardP0.size());
		Assert.assertEquals("Sfumature Giallo Somma dei valori su tutti i dadi Giallo", privateObjectiveCardP0.get(0).getToolTip());
		Assert.assertEquals("Sfumature Viola Somma dei valori su tutti i dadi Viola", privateObjectiveCardP0.get(1).getToolTip());

	}

	/**
	 * Test to verify extraction of window frame cards (single player)
	 */
	@Test
	public void testFrameExtraction1Player()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.getModel().getMainBoard().setPlayerCount(1);
		game.getModel().setState(new FrameSelectionState());

		Assert.assertEquals(1, game.getModel().getMainBoard().getData().getPlayerCount());
		Assert.assertFalse(game.getModel().getPlayerBoard(0).getData().getWindowFrameIsSet());
		Assert.assertEquals(2, game.getModel().getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame().length);

		WindowFrameCouple[] windowFrameCouples2 = game.getModel().getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame();

		Assert.assertEquals("Chromatic Splendor", windowFrameCouples2[0].getWindowFrame(0).getName());
		Assert.assertEquals("Comitas", windowFrameCouples2[0].getWindowFrame(1).getName());
		Assert.assertEquals("Fractal Drops", windowFrameCouples2[1].getWindowFrame(0).getName());
		Assert.assertEquals("Ripples of Light", windowFrameCouples2[1].getWindowFrame(1).getName());

	}

	/**
	 * Test to verify extraction of window frame cards (MultiPlayer)
	 */
	@Test
	public void testFrameExtraction4Player()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.getModel().setState(new FrameSelectionState());

		Assert.assertEquals(4, game.getModel().getMainBoard().getData().getPlayerCount());

		Assert.assertFalse(game.getModel().getPlayerBoard(0).getData().getWindowFrameIsSet());
		Assert.assertFalse(game.getModel().getPlayerBoard(1).getData().getWindowFrameIsSet());
		Assert.assertFalse(game.getModel().getPlayerBoard(2).getData().getWindowFrameIsSet());
		Assert.assertFalse(game.getModel().getPlayerBoard(3).getData().getWindowFrameIsSet());

		Assert.assertEquals(2, game.getModel().getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame().length);

		WindowFrameCouple[] windowFrameCouples2 = game.getModel().getPlayerBoard(0).getData().getExtractedWindowFrameCouplesWindowFrame();

		Assert.assertEquals("Aurorae Mangnificus", windowFrameCouples2[0].getWindowFrame(0).getName());
		Assert.assertEquals("Aurora Sagradis", windowFrameCouples2[0].getWindowFrame(1).getName());
		Assert.assertEquals("Fractal Drops", windowFrameCouples2[1].getWindowFrame(0).getName());
		Assert.assertEquals("Ripples of Light", windowFrameCouples2[1].getWindowFrame(1).getName());

		Assert.assertEquals(2, game.getModel().getPlayerBoard(3).getData().getExtractedWindowFrameCouplesWindowFrame().length);

		windowFrameCouples2 = game.getModel().getPlayerBoard(3).getData().getExtractedWindowFrameCouplesWindowFrame();

		Assert.assertEquals("Chromatic Splendor", windowFrameCouples2[0].getWindowFrame(0).getName());
		Assert.assertEquals("Comitas", windowFrameCouples2[0].getWindowFrame(1).getName());
		Assert.assertEquals("Lux Mundi", windowFrameCouples2[1].getWindowFrame(0).getName());
		Assert.assertEquals("Lux Astram", windowFrameCouples2[1].getWindowFrame(1).getName());

	}

	/**
	 * Test - frame selection  (single player)
	 */
	@Test
	public void testFrameSelection1Player()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.getModel().getMainBoard().setPlayerCount(1);
		game.getModel().setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, 1, 0));
		game.processAction();
		Assert.assertEquals("Fractal Drops", game.getModel().getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Round State", game.getModel().getMainBoard().getData().getGameState().getName());

	}

	/**
	 * Test - frame selection (MultiPlayer)
	 */
	@Test
	public void testFrameSelection4Player()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.getModel().setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, 0, 0));
		game.processAction();

		game.sendAction(new FrameSetAction(1, 0, 1));
		game.processAction();

		game.sendAction(new FrameSetAction(2, 1, 0));
		game.processAction();

		game.sendAction(new FrameSetAction(3, 1, 1));
		game.processAction();

		Assert.assertEquals("Aurorae Mangnificus", game.getModel().getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Shadow Thief", game.getModel().getPlayerBoard(1).getData().getWindowFrame().getName());
		Assert.assertEquals("Water of Life", game.getModel().getPlayerBoard(2).getData().getWindowFrame().getName());
		Assert.assertEquals("Lux Astram", game.getModel().getPlayerBoard(3).getData().getWindowFrame().getName());

		Assert.assertEquals("Round State", game.getModel().getMainBoard().getData().getGameState().getName());

	}

	/**
	 * Test - frame selection - empty window frame (Single player)
	 */
	@Test
	public void testFrameSelection1PlayerEmptyWindowFrame()
	{
		game.getModel().getMainBoard().setPlayerCount(1);
		game.getModel().setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, -1, 0));
		game.processAction();

		Assert.assertEquals("", game.getModel().getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Round State", game.getModel().getMainBoard().getData().getGameState().getName());

	}

	/**
	 * Test - frame selection - empty window frame (MultiPlayer)
	 */
	@Test
	public void testFrameSelection1PlayerEmptyError()
	{
		List<WindowFrameCouple>  windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		for(int i=0; i<windowFrameCouples.size(); i++)
		{
			game.sendAction(new AddWindowFrameCoupleAction(windowFrameCouples.get(i)));
		}
		game.processAllPendingAction();
		game.getModel().getMainBoard().setPlayerCount(1);
		game.getModel().setState(new FrameSelectionState());

		game.sendAction(new FrameSetAction(0, 2, 0));
		game.processAction();

		Assert.assertEquals("", game.getModel().getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Frame selection", game.getModel().getMainBoard().getData().getGameState().getName());

		game.sendAction(new FrameSetAction(0, 1, 0));
		game.processAction();
		Assert.assertEquals("Fractal Drops", game.getModel().getPlayerBoard(0).getData().getWindowFrame().getName());
		Assert.assertEquals("Round State", game.getModel().getMainBoard().getData().getGameState().getName());


	}

}
