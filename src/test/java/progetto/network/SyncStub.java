package progetto.network;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncStub implements ISync {
	private static final Logger LOGGER = Logger.getLogger(SyncStub.class.getName());
	private ArrayList<String> ls = new ArrayList<String>();
	private String full = "";

	public synchronized void sendString(String s) {
		ls.add(s);
		full = full + s;
	}

	public synchronized boolean isStringGood(String s, int senderID) {
		return s.equals("SyncMeUp");
	}

	public synchronized String getHash(int index) {
		String toReturn = "";
		if (index > ls.size())
			toReturn = "BROKEN HASH!!!!!";
		else {
			for (int a = 0; a < index; a++) {
				toReturn = toReturn + ls.get(a);
			}
		}
		return toReturn;
	}

	public synchronized String getHash() {

		return full;
	}

	public synchronized int getStringCount() {
		return ls.size();
	}

	public synchronized String getString(int index) {
		return ls.get(index);
	}

	public synchronized void clear() {
		ls.clear();
		full = "";
	}

	public synchronized List<String> getAllString() {
		ArrayList<String> s = new ArrayList<String>();
		s.addAll(ls);
		return s;
	}
}