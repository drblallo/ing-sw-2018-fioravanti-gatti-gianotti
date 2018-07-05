package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Test WindowFrameCouple class
 */
public class TestWindowFrameCouple extends TestCase {

	List<WindowFrameCouple> windowFrameCouples;
	WindowFrameCouple windowFrameCouple;

	@Before
	public void setUp()
	{
		windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		windowFrameCouple = new WindowFrameCouple();

	}

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor()
	{
		Assert.assertNotNull(windowFrameCouple.getWindowFrame(0));
		Assert.assertNotNull(windowFrameCouple.getWindowFrame(1));

	}

	/**
	 * Test getter
	 */
	@Test
	public void testGetter()
	{
		windowFrameCouple = windowFrameCouples.get(2);

		Assert.assertEquals(null, windowFrameCouple.getWindowFrame(0).getColorBond(0,0));
		Assert.assertEquals(GameColor.BLUE, windowFrameCouple.getWindowFrame(0).getColorBond(3,0));
		Assert.assertEquals(Value.ONE, windowFrameCouple.getWindowFrame(0).getValueBond(2,4));
		Assert.assertEquals(null, windowFrameCouple.getWindowFrame(0).getValueBond(1,1));
		Assert.assertEquals(null, windowFrameCouple.getWindowFrame(0).getColorBond(1,1));

		Assert.assertEquals(3, windowFrameCouple.getWindowFrame(0).getFavorToken());

		Assert.assertEquals("Fractal Drops", windowFrameCouple.getWindowFrame(0).getName());


		Assert.assertEquals(null, windowFrameCouple.getWindowFrame(1).getColorBond(0,0));
		Assert.assertEquals(GameColor.YELLOW, windowFrameCouple.getWindowFrame(1).getColorBond(3,0));
		Assert.assertEquals(Value.SIX, windowFrameCouple.getWindowFrame(1).getValueBond(2,4));
		Assert.assertEquals(null, windowFrameCouple.getWindowFrame(1).getValueBond(1,1));
		Assert.assertEquals(null, windowFrameCouple.getWindowFrame(1).getColorBond(1,1));

		Assert.assertEquals(5, windowFrameCouple.getWindowFrame(1).getFavorToken());

		Assert.assertEquals("Ripples of Light", windowFrameCouple.getWindowFrame(1).getName());

	}

	/**
	 * Test getter - fail - wrong parameter
	 */
	@Test
	public void testGetterFail()
	{
		Assert.assertNull(windowFrameCouple.getWindowFrame(2));

	}

}
