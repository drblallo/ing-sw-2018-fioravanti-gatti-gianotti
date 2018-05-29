package progetto.game;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDiceBag extends TestCase {

	DiceBag diceBag;

	@Before
	public void setUp()
	{
		diceBag = new DiceBag();
	}

	@Test
	public void testConstructor() {

		Assert.assertEquals(90, diceBag.getNumberOfDices());

		for(int i=0; i<18; i++)
			Assert.assertEquals(Color.YELLOW, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(Color.RED, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(Color.BLUE, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(Color.GREEN, diceBag.draw(0));

		for(int i=0; i<18; i++)
			Assert.assertEquals(Color.PURPLE, diceBag.draw(0));

	}


	@Test
	public void testDraw() {

		Assert.assertEquals(null, diceBag.draw(90));
		Assert.assertEquals(Color.PURPLE, diceBag.draw(85));
		Assert.assertEquals(Color.GREEN, diceBag.draw(70));
		Assert.assertEquals(Color.BLUE, diceBag.draw(50));
		Assert.assertEquals(Color.RED, diceBag.draw(35));
		Assert.assertEquals(Color.YELLOW, diceBag.draw(15));

	}

	@Test
	public void testDrawFail() {

		Assert.assertNull(diceBag.draw(100));
		for(int i=0; i<90; i++)
			diceBag.draw(0);
		Assert.assertNull(diceBag.draw(0));

	}


	@Test
	public void testAdd()
	{
		Assert.assertEquals(90, diceBag.getNumberOfDices());

		diceBag.draw(0);
		Assert.assertEquals(89, diceBag.getNumberOfDices());

		diceBag.add(Color.YELLOW);
		Assert.assertEquals(90, diceBag.getNumberOfDices());
		Assert.assertEquals(Color.YELLOW, diceBag.draw(89));

	}

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
			diceBag.add(Color.YELLOW);
			i++;
		}
	}

}