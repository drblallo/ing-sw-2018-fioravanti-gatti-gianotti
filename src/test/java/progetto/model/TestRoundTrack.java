package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRoundTrack extends TestCase {

	RoundTrack roundTrack;
	
	@Before
	public void setUp()
	{
		roundTrack = new RoundTrack();
	}

	@Test
	public void testConstructor()
	{
		Assert.assertEquals(0, roundTrack.getData().firstFreePosition());
		for(int i=0; i<10; i++)
		{
			Assert.assertEquals(true, roundTrack.getData().isFree(i));
		}
	}

	@Test
	public void testIsFree()
	{
		for(int i=0; i<10; i++)
		{
			Assert.assertEquals(true, roundTrack.getData().isFree(i));
		}

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		Assert.assertFalse(roundTrack.getData().isFree(0));
		Assert.assertTrue(roundTrack.getData().isFree(1));
	}

	@Test
	public void testFirstFreePosition()
	{
		Assert.assertEquals(0, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 0);
		Assert.assertEquals(1, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 1);
		Assert.assertEquals(2, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 2);
		Assert.assertEquals(3, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 3);
		Assert.assertEquals(4, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 4);
		Assert.assertEquals(5, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 5);
		Assert.assertEquals(6, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 6);
		Assert.assertEquals(7, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 7);
		Assert.assertEquals(8, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 8);
		Assert.assertEquals(9, roundTrack.getData().firstFreePosition());

		roundTrack.add(new Dice(Value.ONE, GameColor.YELLOW), 9);
		Assert.assertEquals(-1, roundTrack.getData().firstFreePosition());

	}

	@Test
	public void testAdd()
	{
		Dice dice = new Dice(Value.ONE, GameColor.YELLOW);
		Dice dice1 = new Dice(Value.FOUR, GameColor.YELLOW);
		Dice dice2 = new Dice(Value.FOUR, GameColor.BLUE);

		roundTrack.add(dice, 0);
		Assert.assertEquals(dice, roundTrack.getData().getDice(0, 0));
		Assert.assertFalse(roundTrack.getData().isFree(0));
		Assert.assertTrue(roundTrack.getData().isFree(1));

		roundTrack.add(dice1, 0);
		Assert.assertEquals(dice1, roundTrack.getData().getDice(0, 1));

		roundTrack.add(dice2, 1);
		Assert.assertEquals(dice2, roundTrack.getData().getDice(1, 0));
	}

	@Test
	public void testAddFail()
	{
		RoundTrackData roundTrackData = roundTrack.getData();
		Dice dice = new Dice(Value.ONE, GameColor.YELLOW);
		roundTrack.add(dice, 15);
		Assert.assertEquals(roundTrackData, roundTrack.getData());

	}

	@Test
	public void testChange()
	{
		Dice dice = new Dice(Value.ONE, GameColor.YELLOW);
		Dice dice1 = new Dice(Value.FOUR, GameColor.YELLOW);
		Dice dice2 = new Dice(Value.FOUR, GameColor.BLUE);
		Dice dice3 = new Dice(Value.TWO, GameColor.GREEN);
		Dice dice4 = new Dice(Value.FOUR, GameColor.YELLOW);

		roundTrack.add(dice, 0);
		roundTrack.add(dice1, 0);
		roundTrack.add(dice2, 1);

		roundTrack.change(0, 1, dice3);
		Assert.assertEquals(dice3, roundTrack.getData().getDice(0, 1));

		roundTrack.change(1, 0, dice4);
		Assert.assertEquals(dice4, roundTrack.getData().getDice(1, 0));

		Assert.assertEquals(dice, roundTrack.getData().getDice(0, 0));

		Assert.assertEquals(dice3, roundTrack.getData().getDice(0, 1));

	}

	@Test
	public void testChangeFail()
	{
		RoundTrackData roundTrackData = roundTrack.getData();
		Dice dice = new Dice(Value.ONE, GameColor.YELLOW);

		roundTrack.change(0, 1, dice);
		Assert.assertEquals(roundTrackData, roundTrack.getData());

		roundTrack.change(15, 0, dice);
		Assert.assertEquals(roundTrackData, roundTrack.getData());

	}

	public void testGetDice() {

		roundTrack.add(new Dice(Value.THREE, GameColor.PURPLE), 0);

		roundTrack.add(new Dice(Value.TWO, GameColor.BLUE), 2);

		assertEquals(true, roundTrack.getData().isFree(1));

		roundTrack.change(2, 0, new Dice(Value.ONE, GameColor.GREEN));

		assertEquals(true, roundTrack.getData().isFree(1));
		assertEquals(false, roundTrack.getData().isFree(0));
		assertEquals(false, roundTrack.getData().isFree(2));

		assertEquals(GameColor.GREEN, roundTrack.getData().getDice(2,0).getGameColor());
		assertEquals(Value.ONE, roundTrack.getData().getDice(2,0).getValue());

		assertEquals(null, roundTrack.getData().getDice(5,5));

	}
}