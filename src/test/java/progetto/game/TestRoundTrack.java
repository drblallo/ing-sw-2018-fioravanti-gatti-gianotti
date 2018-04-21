package progetto.game;

import junit.framework.TestCase;

public class TestRoundTrack extends TestCase {

	public void test1() {

		RoundTrack rt = new RoundTrack();

		assertEquals(true, rt.isFree(0));
		assertEquals(true, rt.isFree(1));

		assertEquals(0, rt.firstFreePosition());

		rt.add(new Dice(Value.THREE, Color.PURPLE), 0);

		assertEquals(false, rt.isFree(0));
		assertEquals(true, rt.isFree(1));

		assertEquals(1, rt.firstFreePosition());

		rt.add(new Dice(Value.TWO, Color.BLUE), 0);

		assertEquals(false, rt.isFree(0));
		assertEquals(true, rt.isFree(1));
		assertEquals(1, rt.firstFreePosition());

		rt.add(new Dice(Value.FOUR, Color.YELLOW), 1);

		assertEquals(false, rt.isFree(0));
		assertEquals(false, rt.isFree(1));
		assertEquals(true, rt.isFree(2));
		assertEquals(2, rt.firstFreePosition());

		Dice dice1 = rt.getDice(0, 1);

		assertEquals(Value.TWO, dice1.getValue());
		assertEquals(Color.BLUE, dice1.getColor());

		assertEquals(Value.TWO, rt.getValue(0, 1));
		assertEquals(Color.BLUE, rt.getColor(0, 1));

		Dice dice2 = rt.getDice(1, 0);

		assertEquals(Value.FOUR, dice2.getValue());
		assertEquals(Color.YELLOW, dice2.getColor());

		assertEquals(Value.FOUR, rt.getValue(1, 0));
		assertEquals(Color.YELLOW, rt.getColor(1, 0));

		rt.add(new Dice(Value.FOUR, Color.YELLOW), 2);
		rt.add(new Dice(Value.FOUR, Color.YELLOW), 3);
		rt.add(new Dice(Value.FOUR, Color.YELLOW), 4);
		rt.add(new Dice(Value.FOUR, Color.YELLOW), 5);
		rt.add(new Dice(Value.FOUR, Color.YELLOW), 6);
		rt.add(new Dice(Value.FOUR, Color.YELLOW), 7);

		rt.add(new Dice(Value.FOUR, Color.YELLOW), 8);
		rt.add(new Dice(Value.ONE, Color.PURPLE), 8);
		rt.add(new Dice(Value.THREE, Color.RED), 8);

		rt.add(new Dice(Value.FOUR, Color.YELLOW), 9);
		rt.add(new Dice(Value.FOUR, Color.YELLOW), 10);

		assertEquals(-1, rt.firstFreePosition());

		assertEquals(Value.ONE, rt.getValue(8, 1));
		assertEquals(Color.PURPLE, rt.getColor(8, 1));

		rt.change(8, 1, new Dice(Value.TWO, Color.GREEN));

		assertEquals(Value.TWO, rt.getValue(8, 1));
		assertEquals(Color.GREEN, rt.getColor(8, 1));

		rt.change(8, 0, new Dice(Value.FIVE, Color.BLUE));

		assertEquals(Value.FIVE, rt.getValue(8, 0));
		assertEquals(Color.BLUE, rt.getColor(8, 0));

		rt.change(8, 2, new Dice(Value.FOUR, Color.RED));

		assertEquals(Value.FOUR, rt.getValue(8, 2));
		assertEquals(Color.RED, rt.getColor(8, 2));

	}
}