package progetto.integration.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import progetto.model.EndTurnAction;
import progetto.view.commandline.EchoCommand;
import progetto.view.commandline.ICommandProcessor;
import progetto.view.commandline.StackCommandProcessor;

public class TestCommandProcessorBuilder {

	private StackableCommandProcessorBuilder commandProcessorBuilder;
	private ClientController controller;

	@Before
	public void setUp()
	{
		controller = new ClientController();
		commandProcessorBuilder = new StackableCommandProcessorBuilder(new StackCommandProcessor(), controller);
		commandProcessorBuilder.addNested("root", "root");
	}


	@Test
	public void testAddLayer()
	{

		StackCommandProcessor stc = commandProcessorBuilder
				.addNested("1", "1")
				.addReturn("return")
				.addNested("2", "2")
				.getStackCommandProcessor();

		Assert.assertTrue(stc.existCommand("1"));
		Assert.assertTrue(stc.existCommand("2"));
		stc.execute("1");
		Assert.assertTrue(stc.existCommand("return"));
	}

	@Test
	public void testPop()
	{
		StackCommandProcessor stc = commandProcessorBuilder
				.addNested("1", "1")
				.addNested("2", "2")
				.addNested("2", "2")
				.addNested("2", "2")
				.addNested("2", "2")
				.addNested("2", "2")
				.pop()
				.pop()
				.pop()
				.pop()
				.pop()
				.pop()
				.addNested("3", "3")
				.getStackCommandProcessor();


		Assert.assertFalse(stc.existCommand("2"));
		Assert.assertTrue(stc.existCommand("1"));
		Assert.assertTrue(stc.existCommand("3"));
	}


	@Test
	public void testAddLeaf()
	{
		StackCommandProcessor stc = commandProcessorBuilder
				.addLeaf(new EchoCommand())
				.getStackCommandProcessor();

		Assert.assertTrue(stc.existCommand("echo"));
	}

	@Test
	public void testReturn()
	{
		StackCommandProcessor stc = commandProcessorBuilder
				.addNested("1", "1")
				.addNested("1", "1")
				.addNested("1", "1")
				.addNested("1", "1")
				.addNested("1", "1")
				.addNested("1", "1")
				.addNested("1", "1")
				.addReturn("return", "root")
				.getStackCommandProcessor();

		stc.execute("1");
		stc.execute("1");
		stc.execute("1");
		stc.execute("1");
		stc.execute("1");
		stc.execute("1");
		stc.execute("1");

		Assert.assertTrue(stc.existCommand("return"));

		stc.execute("return");
		Assert.assertEquals("root", stc.getName());

	}

	@Test
	public void testLambdaCommands()
	{
		ICommandProcessor stc = commandProcessorBuilder
				.addLeaf
				(
						"numConnessioni",
						"stampa il numero di connessioni",
						controller -> controller.getConnectionCount()+""
				)
				.addLeaf
				(
						"fineTurno",
						"termina il turno",
						controller ->
						{
							controller.sendAction(new EndTurnAction());
							return "command sent";
						}

				)
				.getStackCommandProcessor();


		Assert.assertTrue(stc.existCommand("numConnessioni"));
		Assert.assertEquals("stampa il numero di connessioni", stc.getCommand("numConnessioni").getHelp());
		Assert.assertEquals("0", stc.execute("numConnessioni"));
	}
}
