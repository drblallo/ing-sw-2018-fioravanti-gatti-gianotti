package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test dice bag
 */
public class TestDiceBag extends TestCase {

	DiceBag diceBag;

	@Before
	public void setUp()
	{
		diceBag = new DiceBag();
	}

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor() {

		Assert.assertEquals(90, diceBag.getNumberOfDices());

		for(int i=0; i<18; i++)
			Assert.assertEquals(GameColor.YELLOW, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(GameColor.RED, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(GameColor.BLUE, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(GameColor.GREEN, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(GameColor.PURPLE, diceBag.draw(0));

	}

	/**
	 * Test draw a dice
	 */
	@Test
	public void testDraw() {

		Assert.assertEquals(null, diceBag.draw(90));
		Assert.assertEquals(GameColor.PURPLE, diceBag.draw(85));
		Assert.assertEquals(GameColor.GREEN, diceBag.draw(70));
		Assert.assertEquals(GameColor.BLUE, diceBag.draw(50));
		Assert.assertEquals(GameColor.RED, diceBag.draw(35));
		Assert.assertEquals(GameColor.YELLOW, diceBag.draw(15));

	}

	/**
	 * Test draw a dice - wrong index
	 */
	@Test
	public void testDrawFail() {

		Assert.assertNull(diceBag.draw(100));
		for(int i=0; i<90; i++)
			diceBag.draw(0);
		Assert.assertNull(diceBag.draw(0));

	}

	/**
	 * Test add a dice
	 */
	@Test
	public void testAdd()
	{
		Assert.assertEquals(90, diceBag.getNumberOfDices());

		diceBag.draw(0);
		Assert.assertEquals(89, diceBag.getNumberOfDices());

		diceBag.add(GameColor.YELLOW);
		Assert.assertEquals(90, diceBag.getNumberOfDices());
		Assert.assertEquals(GameColor.YELLOW, diceBag.draw(89));

	}

	/**
	 * Test get number of dices
	 */
	@Test
	public void testNumberOfDices()
	{
		Assert.assertEquals(90, diceBag.getNumberOfDices());

		int i=90;

		while(diceBag.getNumberOfDices()>0)
		{
			Assert.assertEquals(i, diceBag.getNumberOfDices());
			diceBag.draw(0);
			i--;
		}

		while(diceBag.getNumberOfDices()<=90)
		{
			Assert.assertEquals(i, diceBag.getNumberOfDices());
			diceBag.add(GameColor.YELLOW);
			i++;
		}
	}

}