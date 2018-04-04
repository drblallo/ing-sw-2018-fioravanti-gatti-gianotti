package progetto.network;

/**
 * This interface is used to connect the network to whatever we want to keep synchronized across the net.
 * It is assumed that the sequence of string is enough to ensurer that the state of the object is unique.
 *
 */
public interface ISyncInterface {

	/**
	 * Send the string to process
	 */
	void sendString(String s);

	/**
	 * @return true if the string is compatible with the senderID
	 */
	boolean isStringGood(String s, int senderID);

	/**
	 *
	 * @return hash of the state of the object.
	 */
	String getHash();

	/**
	 *
	 * @return the count of string that the object has received
	 */
	int getStringCount();

	/**
	 * @return the string received at index
	 */
	String getString(int index);

	/**
	 * reset the state of the object as if it was just been created.
	 */
	void clear();
}
