package progetto.integration.client;

import progetto.view.commandline.ICommand;
import progetto.view.commandline.PopCommand;
import progetto.view.commandline.StackCommandProcessor;
import progetto.view.commandline.StackableCommandProcessor;

import java.util.Deque;
import java.util.LinkedList;

public class StackableCommandProcessorBuilder {

	private Deque<StackableCommandProcessor> stack = new LinkedList<>();
	private StackCommandProcessor stackCommandProcessor;
	private ClientController controller;

	public StackableCommandProcessorBuilder(StackCommandProcessor base, ClientController controller)
	{
		stackCommandProcessor = base;
		this.controller = controller;
	}

	public StackableCommandProcessorBuilder addNested(String name, String pushingMessage)
	{
		StackableCommandProcessor stc = new StackableCommandProcessor(name, stackCommandProcessor, pushingMessage);
		if (!stack.isEmpty())
			stack.peek().registerCommand(stc);
		stack.push(stc);
		return this;
	}

	public StackableCommandProcessorBuilder pop()
	{
		stack.pop();
		return this;
	}

	public StackableCommandProcessorBuilder addLeaf(ICommand commandProcessor)
	{
		stack.peek().registerCommand(commandProcessor);
		return this;
	}

	public StackableCommandProcessorBuilder addReturn(String name, String target)
	{
		StackableCommandProcessor s = stack.pop();
		s.registerCommand(new PopCommand(stackCommandProcessor, name, target));
		return this;
	}

	public StackableCommandProcessorBuilder addReturn(String name)
	{
		StackableCommandProcessor s = stack.pop();
		s.registerCommand(new PopCommand(stackCommandProcessor, name, stack.peek().getName()));
		return this;
	}

	public StackCommandProcessor getStackCommandProcessor()
	{
		StackableCommandProcessor stackableCommandProcessor = stack.pop();
		while (!stack.isEmpty())
			stackableCommandProcessor = stack.pop();
		stackCommandProcessor.pushProcessor(stackableCommandProcessor);
		return stackCommandProcessor;
	}

	public StackableCommandProcessorBuilder addLeaf(String name, String help, IControllerExecutible lamda)
	{
		stack.peek().registerCommand(new ControllerAwareCommand(name, controller, help, lamda));
		return this;
	}
}
