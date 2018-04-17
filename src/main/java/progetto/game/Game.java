package progetto.game;

public final class Game {

	private CommandQueue commandQueue = new CommandQueue();

	public Game() {
		throw new UnsupportedOperationException();
	}

	public CommandQueue getCommandQueue() {
		return commandQueue;
	}

}
