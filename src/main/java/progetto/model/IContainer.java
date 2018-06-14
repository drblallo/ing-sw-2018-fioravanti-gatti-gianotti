package progetto.model;

import java.io.Serializable;

public interface IContainer<T extends Serializable> {
	T getData();
	void setData(T data);
}
