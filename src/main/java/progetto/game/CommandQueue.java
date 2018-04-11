package progetto.game;

public class CommandQueue implements IProcessor<AbstractGameAction> {
	public void sendItem(AbstractGameAction item) {
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

	public AbstractGameAction getPendingItem(int index) {
		throw new  UnsupportedOperationException();
	}

	public void getPastItemCount() {
		throw new  UnsupportedOperationException();
	}

	public AbstractGameAction getPastItem(int index) {
		throw new  UnsupportedOperationException();
	}

}
