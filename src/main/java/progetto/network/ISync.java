package progetto.network;

import progetto.utils.Callback;

import java.io.Serializable;
import java.util.List;

/**
 * This interface is used to connect the network to whatever we want to keep synchronized across the net.
 * It is assumed that the sequence of string is enough to ensurer that the state of the object is unique.
 * @author Massimo
 */
public interface ISync {

	/**
	 * called every update loop
	 */
	String update();

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

	/**
	 *
	 * @return the list of all serializable that where received
	 */
	List<Serializable> getAllItems();

	/**
	 *
	 * @return the enforce that is called when by the sync object to notify what changed in the server
	 */
	Callback<IEnforce> getEnforceCallback();

	/**
	 *
	 * @return all the enforces that must be sent to a new player
	 */
	List<IEnforce> getNewPlayerEnforces();
}
