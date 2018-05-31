package progetto.model;

import org.junit.Assert;
import org.junit.Test;

public class TestWindowFrameCoupleArray {

	WindowFrameCoupleArray windowFrameCoupleArray;

	@Test
	public void testRead()
	{
		windowFrameCoupleArray = new WindowFrameCoupleArray();
		Assert.assertEquals(12, windowFrameCoupleArray.getWindowFrameCouples().size());
		Assert.assertEquals("Water of Life", windowFrameCoupleArray.getWindowFrameCouples().get(0).getWindowFrame(0).getName());
		Assert.assertEquals("Firmitas", windowFrameCoupleArray.getWindowFrameCouples().get(11).getWindowFrame(1).getName());
		Assert.assertEquals(4, windowFrameCoupleArray.getWindowFrameCouples().get(5).getWindowFrame(1).getFavorToken());
		Assert.assertEquals(Color.YELLOW, windowFrameCoupleArray.getWindowFrameCouples().get(1).getWindowFrame(1).getColorBond(1, 0));
		Assert.assertEquals(Color.YELLOW, windowFrameCoupleArray.getWindowFrameCouples().get(2).getWindowFrame(0).getColorBond(3, 1));

	}

	@Test
	public void testReadWithPath()
	{
		windowFrameCoupleArray = new WindowFrameCoupleArray("windowFrameCouples.json");
		Assert.assertEquals(12, windowFrameCoupleArray.getWindowFrameCouples().size());

	}

	@Test
	public void testReadFailFileNotFound()
	{
		windowFrameCoupleArray = new WindowFrameCoupleArray("windowFrameCouplesTest.json");
		Assert.assertEquals(0, windowFrameCoupleArray.getWindowFrameCouples().size());

	}

	@Test
	public void testReadFailFileWithError()
	{
		windowFrameCoupleArray = new WindowFrameCoupleArray("src/test/windowFrameCouplesTest.json");
		Assert.assertEquals(0, windowFrameCoupleArray.getWindowFrameCouples().size());

	}

	@Test
	public void testReadFailEmptyPath()
	{
		windowFrameCoupleArray = new WindowFrameCoupleArray("");
		Assert.assertEquals(0, windowFrameCoupleArray.getWindowFrameCouples().size());

		windowFrameCoupleArray.readWindowFrameCouples("");
		Assert.assertEquals(0, windowFrameCoupleArray.getWindowFrameCouples().size());

	}

}
