package progetto.commandline;

import org.junit.Assert;
import org.junit.Test;

public class TestListCommand {

	@Test
	public void testListCommand()
	{
		CommandProcessor c = new CommandProcessor("main");
		ListCommand l = new ListCommand(c);
		c.registerCommand(l);
		Assert.assertEquals("list\n", c.execute("list"));
		Assert.assertEquals("return the name of all commands", l.getHelp());
	}
}
