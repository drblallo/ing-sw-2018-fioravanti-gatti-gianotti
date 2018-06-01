package progetto.network;

import progetto.utils.Callback;

import java.io.Serializable;
import java.util.List;

/**
 * This interface is used to connect the network to whatever we want to keep synchronized across the net.
 * It is assumed that the sequence of string is enough to ensurer that the state of the object is unique.
 */
public interface ISync {

	/**
	 * Send the string to process
	 */
	void sendItem(Serializable s);

	/**
	 * @return true if the string is compatible with the senderID
	 */
	boolean isItemGood(Serializable s, int senderID);

	/**
	 * @param index index of state of the requested hash
	 * @return hash of the state of the object.
	 */
	int getHash(int index);

	/**
	 * @return the count of string that the object has received
	 */
	int getItemCount();

	/**
	 * @return latest hash
	 */
	int getHash();

	/**
	 * reset the state of the object as if it was just been created.
	 */
	void clear();

	List<Serializable> getAllItems();

	Callback<IEnforce> getEnforceCallback();
	List<IEnforce> getNewPlayerEnforces();
}
