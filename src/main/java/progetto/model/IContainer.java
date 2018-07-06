package progetto.model;

import java.io.Serializable;

/**
 * contains an object that can be retrieved
 * @author Michele
 * @param <T> extends Serializable
 */
public interface IContainer<T extends Serializable> {

	/**
	 *
	 * @return the contained object
	 */
	T getData();

	/**
	 * set the contained object
	 * @param data object to be set in the container
	 */
	void setData(T data);
}
