package progetto.game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandQueue implements IProcessor<AbstractGameCommand> {
	public void sendItem(AbstractGameCommand item) {
		throw new  NotImplementedException();
	}

	public void processItems(int itemToProcessCount) {
		throw new  NotImplementedException();
	}

	public void processAllItems() {
		throw new  NotImplementedException();
	}

	public int getPendingItemsCount() {
		throw new  NotImplementedException();
	}

	public AbstractGameCommand getPendingItem(int index) {
		throw new  NotImplementedException();
	}

	public void getPastItemCount() {
		throw new  NotImplementedException();
	}

	public AbstractGameCommand getPastItem(int index) {
		throw new  NotImplementedException();
	}

}
