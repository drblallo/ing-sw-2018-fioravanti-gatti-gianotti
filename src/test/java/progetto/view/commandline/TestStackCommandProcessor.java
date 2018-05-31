package progetto.view.commandline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStackCommandProcessor extends AbstractCommandProcessorTest {

	@Before
	public void setup(){

		StackCommandProcessor nuovo = new StackCommandProcessor();
		nuovo.pushProcessor(new StackableCommandProcessor("main", nuovo, ""));

		this.nuovo = nuovo;

	}
	@Test
	public void testStackCommandProcessorConstructor()
	{
		StackCommandProcessor stack = new StackCommandProcessor();
		StackableCommandProcessor stackacble = new StackableCommandProcessor("root", stack, "ROOT");

		Assert.assertEquals("ROOT\n", stackacble.execute(""));
		Assert.assertEquals(stack.peekCommandProcessor(), stackacble);
	}

	@Test
	public void testStackTransition()
	{
		StackCommandProcessor stack = new StackCommandProcessor();
		StackableCommandProcessor stackacble = new StackableCommandProcessor("root", stack, "ROOT");
		StackableCommandProcessor s2 = new StackableCommandProcessor("1", stack, "1");

		stackacble.registerCommand(s2);

		stackacble.execute("");

		Assert.assertEquals("1\n", stack.execute("1"));

	}

	@Test
	public void testStackReturn()
	{
		StackCommandProcessor stack = new StackCommandProcessor();
		StackableCommandProcessor stackacble = new StackableCommandProcessor("root", stack, "ROOT");
		StackableCommandProcessor s2 = new StackableCommandProcessor("1", stack, "1");

		stackacble.registerCommand(s2);

		stackacble.execute("");
		stack.execute("1");

		Assert.assertEquals("1", stack.peekCommandProcessor().getName());
		PopCommand pop = new PopCommand(stack, "return", "root");
		s2.registerCommand(pop);
		Assert.assertEquals("ROOT\n1\n", stack.execute("return"));
		Assert.assertEquals("root", stack.peekCommandProcessor().getName());
		Assert.assertEquals("Torna al menu root", pop.getHelp());
	}
}
