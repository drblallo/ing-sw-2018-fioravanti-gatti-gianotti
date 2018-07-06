package progetto.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test WindowFrameCoupleArray (Singleton)
 */
public class TestWindowFrameCoupleArray {

	List<WindowFrameCouple>  windowFrameCouples;

	/**
	 * Test read and get list
	 */
	@Test
	public void testRead()
	{
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		Assert.assertEquals(12, windowFrameCouples.size());
		Assert.assertEquals("Water of Life", windowFrameCouples.get(0).getWindowFrame(0).getName());
		Assert.assertEquals("Firmitas", windowFrameCouples.get(11).getWindowFrame(1).getName());
		Assert.assertEquals(4, windowFrameCouples.get(5).getWindowFrame(1).getFavorToken());
		Assert.assertEquals(GameColor.YELLOW, windowFrameCouples.get(1).getWindowFrame(1).getColorBond(1, 0));
		Assert.assertEquals(GameColor.YELLOW, windowFrameCouples.get(2).getWindowFrame(0).getColorBond(3, 1));

	}

	/**
	 * Test read with path of the file
	 */
	@Test
	public void testReadWithPath()
	{
		WindowFrameCoupleArray.getInstance().readWindowFrameCouples("windowFrameCouples.json");
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		Assert.assertEquals(12, windowFrameCouples.size());

	}

	/**
	 * Test read - fail - file not found
	 */
	@Test
	public void testReadFailFileNotFound()
	{
		WindowFrameCoupleArray.getInstance().readWindowFrameCouples("windowFrameCouplesTest.json");
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		Assert.assertEquals(12, windowFrameCouples.size());

	}

	/**
	 * Test read - fail - file with error
	 */
	@Test
	public void testReadFailFileWithError()
	{
		WindowFrameCoupleArray.getInstance().readWindowFrameCouples("src/test/windowFrameCouplesTest.json");
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		Assert.assertEquals(12, windowFrameCouples.size());

	}

	/**
	 * Test read with path - fail - empty path
	 */
	@Test
	public void testReadFailEmptyPath()
	{
		WindowFrameCoupleArray.getInstance().readWindowFrameCouples("");
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();

		Assert.assertEquals(12, windowFrameCouples.size());

	}

	/**
	 * Test return instance
	 */
	@Test
	public void testReturnInstance()
	{
		WindowFrameCoupleArray windowFrameCoupleArray = WindowFrameCoupleArray.getInstance();
		WindowFrameCoupleArray windowFrameCoupleArray1 = WindowFrameCoupleArray.getInstance();

		Assert.assertEquals(windowFrameCoupleArray, windowFrameCoupleArray1);

	}

}