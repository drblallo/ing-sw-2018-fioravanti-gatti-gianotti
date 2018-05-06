package progetto.game;

import junit.framework.TestCase;

public class TestRoundTrack extends TestCase {

	public void test1() {

		RoundTrack rt = new RoundTrack();

		assertEquals(true, rt.getRoundTrackData().isFree(0));
		assertEquals(true, rt.getRoundTrackData().isFree(1));

		assertEquals(0, rt.getRoundTrackData().firstFreePosition());

		rt.add(new Dice(Value.THREE, Color.PURPLE), 0);

		assertEquals(false, rt.getRoundTrackData().isFree(0));
		assertEquals(true, rt.getRoundTrackData().isFree(1));

		assertEquals(1, rt.getRoundTrackData().firstFreePosition());

		rt.add(new Dice(Value.TWO, Color.BLUE), 0);

		assertEquals(false, rt.getRoundTrackData().isFree(0));
		assertEquals(true, rt.getRoundTrackData().isFree(1));
		assertEquals(1, rt.getRoundTrackData().firstFreePosition());

		rt.add(new Dice(Value.FOUR, Color.YELLOW), 1);

		assertEquals(false, rt.getRoundTrackData().isFree(0));
		assertEquals(false, rt.getRoundTrackData().isFree(1));
		assertEquals(true, rt.getRoundTrackData().isFree(2));
		assertEquals(2, rt.getRoundTrackData().firstFreePosition());

		Dice dice1 = rt.getRoundTrackData().getDice(0, 0);

		assertEquals(Value.THREE, dice1.getValue());
		assertEquals(Color.PURPLE, dice1.getColor());

		dice1 = rt.getRoundTrackData().getDice(0, 1);

		assertEquals(Value.TWO, dice1.getValue());
		assertEquals(Color.BLUE, dice1.getColor());

		assertEquals(Value.TWO, rt.getRoundTrackData().getDice(0,1 ).getValue());
		assertEquals(Color.BLUE, rt.getRoundTrackData().getDice(0,1 ).getColor());

		Dice dice2 = rt.getRoundTrackData().getDice(1, 0);

		assertEquals(Value.FOUR, dice2.getValue());
		assertEquals(Color.YELLOW, dice2.getColor());

		assertEquals(Value.FOUR, rt.getRoundTrackData().getDice(1, 0).getValue());
		assertEquals(Color.YELLOW, rt.getRoundTrackData().getDice(1, 0).getColor());

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

		assertEquals(-1, rt.getRoundTrackData().firstFreePosition());

		assertEquals(Value.ONE, rt.getRoundTrackData().getDice(8,1 ).getValue());
		assertEquals(Color.PURPLE, rt.getRoundTrackData().getDice(8,1 ).getColor());

		rt.change(8, 1, new Dice(Value.TWO, Color.GREEN));

		assertEquals(Value.TWO, rt.getRoundTrackData().getDice(8,1 ).getValue());
		assertEquals(Color.GREEN, rt.getRoundTrackData().getDice(8,1 ).getColor());

		rt.change(8, 0, new Dice(Value.FIVE, Color.BLUE));

		assertEquals(Value.FIVE, rt.getRoundTrackData().getDice(8, 0).getValue());
		assertEquals(Color.BLUE, rt.getRoundTrackData().getDice(8,0).getColor());

		rt.change(8, 2, new Dice(Value.FOUR, Color.RED));

		assertEquals(Value.FOUR, rt.getRoundTrackData().getDice(8, 2).getValue());
		assertEquals(Color.RED, rt.getRoundTrackData().getDice(8,2 ).getColor());

	}

	public void test2() {

		RoundTrack rt = new RoundTrack();

		rt.add(new Dice(Value.THREE, Color.PURPLE), 0);

		rt.add(new Dice(Value.TWO, Color.BLUE), 2);

		assertEquals(true, rt.getRoundTrackData().isFree(1));

		rt.change(2, 0, new Dice(Value.ONE, Color.GREEN));

		assertEquals(true, rt.getRoundTrackData().isFree(1));
		assertEquals(false, rt.getRoundTrackData().isFree(0));
		assertEquals(false, rt.getRoundTrackData().isFree(2));

		assertEquals(Color.GREEN, rt.getRoundTrackData().getDice(2,0).getColor());
		assertEquals(Value.ONE, rt.getRoundTrackData().getDice(2,0).getValue());

		assertEquals(null, rt.getRoundTrackData().getDice(5,5));

	}
}