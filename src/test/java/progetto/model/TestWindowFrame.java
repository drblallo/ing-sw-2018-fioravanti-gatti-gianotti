package progetto.model;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestWindowFrame extends TestCase {

	WindowFrame windowFrame;

	@Test
	public void testConstructor()
	{
		windowFrame = new WindowFrame();
		Assert.assertEquals(0, windowFrame.getFavorToken());
		Assert.assertEquals("", windowFrame.getName());
	}

	@Test
	public void testGetter() {

		WindowFrameCoupleArray windowFrameCoupleArray = new WindowFrameCoupleArray();

		windowFrame = windowFrameCoupleArray.getWindowFrameCouples().get(2).getWindowFrame(0);

		Assert.assertEquals(null, windowFrame.getColorBond(0,0));
		Assert.assertEquals(Color.BLUE, windowFrame.getColorBond(3,0));
		Assert.assertEquals(Value.ONE, windowFrame.getValueBond(2,4));
		Assert.assertEquals(null, windowFrame.getValueBond(1,1));
		Assert.assertEquals(null, windowFrame.getColorBond(1,1));

		Assert.assertEquals(3, windowFrame.getFavorToken());

		Assert.assertEquals("Fractal Drops", windowFrame.getName());

	}

}
