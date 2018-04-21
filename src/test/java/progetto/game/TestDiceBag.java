package progetto.game;

import junit.framework.TestCase;

public class TestDiceBag extends TestCase {

	public void test1() {

		DiceBag db = new DiceBag();

		Color color;

		color = db.draw(5);

		assertEquals(Color.YELLOW, color);

		int nDices = db.getNumberOfDices();

		db.add(color);

		assertEquals(nDices+1, db.getNumberOfDices());

	}
}