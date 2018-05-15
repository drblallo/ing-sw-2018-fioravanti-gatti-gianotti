package progetto.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncStub implements ISync {
	private static final Logger LOGGER = Logger.getLogger(SyncStub.class.getName());
	private ArrayList<String> ls = new ArrayList<>();
	private int full = 0;

	public synchronized void sendItem(Serializable s) {
		ls.add((String) s);
		full = full + ((String)s).length();
	}

	public synchronized boolean isItemGood(Serializable s, int senderID) {
		return s.equals("SyncMeUp");
	}

	public synchronized int getHash(int index) {
		int toReturn = 0;
		if (index > ls.size())
			toReturn = -1;
		else {
			for (int a = 0; a < index; a++) {
				toReturn = toReturn + ls.get(a).length();
			}
		}
		return toReturn;
	}

	public synchronized int getHash() {

		return full;
	}

	public synchronized int getItemCount() {
		return ls.size();
	}

	public synchronized String getItem(int index) {
		return ls.get(index);
	}

	public synchronized void clear() {
		ls.clear();
		full = 0;
	}

	public synchronized List<Serializable> getAllItems() {
		ArrayList<Serializable> s = new ArrayList<>();
		s.addAll(ls);
		return s;
	}
}