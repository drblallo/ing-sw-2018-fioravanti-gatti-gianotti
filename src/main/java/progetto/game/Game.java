package progetto.game;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public final class Game {

	private CommandQueue commandQueue = new CommandQueue();

	public Game()
	{
		throw new NotImplementedException();
	}

	public Game(String fullState)
	{
		throw new NotImplementedException();
	}

	public CommandQueue getCommandQueue() {
		return commandQueue;
	}

}
