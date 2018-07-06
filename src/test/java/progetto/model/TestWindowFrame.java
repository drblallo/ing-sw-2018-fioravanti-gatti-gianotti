package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Test WindowFrame class
 */
public class TestWindowFrame extends TestCase {

	WindowFrame windowFrame;

	/**
	 * Test constructor
	 */
	@Test
	public void testConstructor()
	{
		windowFrame = new WindowFrame();
		Assert.assertEquals(0, windowFrame.getFavorToken());
		Assert.assertEquals("", windowFrame.getName());
	}

	/**
	 * Test getter
	 */
	@Test
	public void testGetter() {

		List<WindowFrameCouple> windowFrameCouples = WindowFrameCoupleArray.getInstance().getList();
		
		windowFrame = windowFrameCouples.get(2).getWindowFrame(0);

		Assert.assertEquals(null, windowFrame.getColorBond(0,0));
		Assert.assertEquals(GameColor.BLUE, windowFrame.getColorBond(3,0));
		Assert.assertEquals(Value.ONE, windowFrame.getValueBond(2,4));
		Assert.assertEquals(null, windowFrame.getValueBond(1,1));
		Assert.assertEquals(null, windowFrame.getColorBond(1,1));

		Assert.assertEquals(3, windowFrame.getFavorToken());

		Assert.assertEquals("Fractal Drops", windowFrame.getName());

	}

}
