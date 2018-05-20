package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDicePlacedFrame extends TestCase {

	DicePlacedFrame dicePlacedFrame;

	@Before
	public void setUp()
	{
		dicePlacedFrame = new DicePlacedFrame();
	}

	@Test
	public void testCostructor()
	{
		Assert.assertEquals(0, dicePlacedFrame.getDicePlacedFrameData().getNDices());
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}
	}

	@Test
	public void testGetDice()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);
		Dice dice2 = new Dice (Value.TWO, Color.RED);

		dicePlacedFrame.addDice(dice1, 1, 2);
		Assert.assertEquals(dice1, dicePlacedFrame.getDicePlacedFrameData().getDice(1, 2));

		dicePlacedFrame.addDice(dice2, 1, 3);
		Assert.assertEquals(dice1, dicePlacedFrame.getDicePlacedFrameData().getDice(1, 2));
		Assert.assertEquals(dice2, dicePlacedFrame.getDicePlacedFrameData().getDice(1, 3));
	}

	public void testGetDiceFail()
	{
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}
		Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(-1, 1));
		Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(5, 5));
		Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(3, 4));
		Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(5, 3));
	}

	@Test
	public void testAddDice()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);
		Dice dice2 = new Dice (Value.TWO, Color.RED);
		Dice dice3 = new Dice(Value.SIX, Color.PURPLE);

		dicePlacedFrame.addDice(dice1, 2, 2);
		Assert.assertEquals(1, dicePlacedFrame.getDicePlacedFrameData().getNDices());
		Assert.assertEquals(dice1, dicePlacedFrame.getDicePlacedFrameData().getDice(2, 2));

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if(!(i==2 && j==2))
					Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}

		dicePlacedFrame.addDice(dice2, 0, 0);
		Assert.assertEquals(2, dicePlacedFrame.getDicePlacedFrameData().getNDices());
		Assert.assertEquals(dice2, dicePlacedFrame.getDicePlacedFrameData().getDice(0, 0));

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if(!((i==2 && j==2) || (i==0 && j==0)))
					Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}

		dicePlacedFrame.addDice(dice3, 3, 4);
		Assert.assertEquals(3, dicePlacedFrame.getDicePlacedFrameData().getNDices());
		Assert.assertEquals(dice3, dicePlacedFrame.getDicePlacedFrameData().getDice(3, 4));

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if(!((i==2 && j==2) || (i==0 && j==0) || (i==3 && j==4)))
					Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}

	}

	@Test
	public void testAddDiceFail()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);
		Dice dice2 = new Dice (Value.TWO, Color.RED);
		Dice dice3 = new Dice(Value.SIX, Color.PURPLE);

		dicePlacedFrame.addDice(dice1, 3, 4);

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}

		dicePlacedFrame.addDice(dice2, 1, 1);

		dicePlacedFrame.addDice(dice3, 1, 1);

		Assert.assertEquals(dice2, dicePlacedFrame.getDicePlacedFrameData().getDice(1, 1));

	}

	@Test
	public void testRemove()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);
		Dice dice2 = new Dice (Value.TWO, Color.RED);
		Dice dice3 = new Dice(Value.SIX, Color.PURPLE);

		dicePlacedFrame.addDice(dice1, 2, 2);

		dicePlacedFrame.removeDice(2, 2);

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(i, j));
			}
		}

		dicePlacedFrame.addDice(dice2, 3, 3);
		dicePlacedFrame.addDice(dice3, 3, 4);

		dicePlacedFrame.removeDice(3, 3);
		Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(3, 3));
		Assert.assertEquals(dice3, dicePlacedFrame.getDicePlacedFrameData().getDice(3, 4));
		dicePlacedFrame.removeDice(3, 4);
		Assert.assertNull(dicePlacedFrame.getDicePlacedFrameData().getDice(3, 4));

	}

	@Test
	public void testRemoveFail()
	{
		Dice dice1 = new Dice(Value.FOUR, Color.GREEN);

		dicePlacedFrame.addDice(dice1, 2, 2);

		dicePlacedFrame.removeDice(2, 2);

		Assert.assertNull(dicePlacedFrame.removeDice(2, 2));

		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				Assert.assertNull(dicePlacedFrame.removeDice(i, j));
			}
		}

		Assert.assertNull(dicePlacedFrame.removeDice(-1, 2));
		Assert.assertNull(dicePlacedFrame.removeDice(3, 5));
		Assert.assertNull(dicePlacedFrame.removeDice(5, 1));

	}


}
