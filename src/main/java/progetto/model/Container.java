package progetto.model;

import progetto.utils.AbstractObservable;

import java.io.Serializable;

/**
 * Class that contains a serializable object and that notifies every time the reference of that object is
 * changed
 * @author Michele
 * @param <T> extends Serializable
 */
public class Container<T extends Serializable> extends AbstractObservable<T> implements IContainer<T>
{

	private T data;

	/**
	 * public constructor
	 * @param base the starting value of the data contained by this object
	 */
	public Container(T base)
	{
		data = base;
	}

	/**
	 *
	 * @return the current value of the data contained by this object
	 */
	public T getData()
	{
		return data;
	}

	/**
	 * set the current value of the data contained by this object
	 * @param data data to be set as current value
	 */
	public void setData(T data)
	{
		if (data != this.data) {
			this.data = data;
			change(data);
		}
	}

	/**
	 * set the data of this container to the same of the container passed as an argument
	 * @param cont container to be copied
	 */
	public void setData(Container<T> cont)
	{
		if (cont.getData() != data) {
			data = cont.getData();
			change(data);
		}
	}
}
