package progetto.game;

public class CommandQueue implements IProcessor<AbstractGameCommand> {
	public void sendItem(AbstractGameCommand item) {
		throw new  UnsupportedOperationException();
	}

	public void processItems(int itemToProcessCount) {
		throw new  UnsupportedOperationException();
	}

	public void processAllItems() {
		throw new  UnsupportedOperationException();
	}

	public int getPendingItemsCount() {
		throw new  UnsupportedOperationException();
	}

	public AbstractGameCommand getPendingItem(int index) {
		throw new  UnsupportedOperationException();
	}

	public void getPastItemCount() {
		throw new  UnsupportedOperationException();
	}

	public AbstractGameCommand getPastItem(int index) {
		throw new  UnsupportedOperationException();
	}

}
