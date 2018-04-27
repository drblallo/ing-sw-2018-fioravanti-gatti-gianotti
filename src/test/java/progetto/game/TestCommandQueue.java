package progetto.game;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCommandQueue {

	private CommandQueue queue;

	@Before
	public void setUp()
	{
		queue = new CommandQueue();
	}

	@After
	public void tearDown()
	{
		queue = null;
	}

	@Test
	public void testOffer()
	{
		queue.offer(new SetSeedAction(-1));
		queue.offer(new SetSeedAction(-1));
		Assert.assertEquals(2, queue.getPendingItemsCount());
	}

	@Test
	public void testPeek()
	{
		SetSeedAction action = new SetSeedAction(-1);
		queue.offer(action);
		Assert.assertEquals(action, queue.peekPending());
	}

	@Test
	public void testPoll()
	{

		SetSeedAction action = new SetSeedAction(-1);
		queue.offer(action);
		Assert.assertEquals(action, queue.pollPending());
		Assert.assertEquals(0, queue.getPendingItemsCount());
	}

	@Test
	public void testPastItem()
	{
		SetSeedAction action = new SetSeedAction(-1);
		queue.offer(action);
		queue.pollPending();
		Assert.assertEquals(1, queue.getPastItemCount());
		Assert.assertEquals(action, queue.getPastItem(0));

	}
}
