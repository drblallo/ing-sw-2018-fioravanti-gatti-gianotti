package progetto.view.commandline;

import org.junit.Assert;
import org.junit.Test;
import progetto.controller.EndTurnAction;
import progetto.model.CommandQueue;
import progetto.model.CommandQueueData;

public class TestDifferenceDescriptor {

	@Test
	public void testDifferenceDescriptor()
	{
		CommandQueue commandQueue = new CommandQueue();

		DifferenceDescriptor<CommandQueueData> differenceDescriptor = new DifferenceDescriptor<>
				(
						commandQueue,
						(data1, data2) -> data1.pastSize() != data2.pastSize(),
						(oldData, newData) -> newData.pastSize()
				);

		commandQueue.offer(new EndTurnAction());
		commandQueue.pollPending();

		differenceDescriptor.detach();
		differenceDescriptor.attach(commandQueue);

		Assert.assertTrue(true);
	}
}
