package progetto.model;

import java.io.Serializable;

/**
 * @author Michele
 * @param <T>
 */
public interface IContainer<T extends Serializable> {
	T getData();
	void setData(T data);
}
