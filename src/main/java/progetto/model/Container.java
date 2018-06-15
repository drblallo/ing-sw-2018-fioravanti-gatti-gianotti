package progetto.model;

import progetto.utils.AbstractObservable;

import java.io.Serializable;

public class Container<T extends Serializable> extends AbstractObservable<T> implements IContainer<T>
{

	private T data;

	public Container(T base)
	{
		data = base;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		if (data != this.data) {
			this.data = data;
			change(data);
		}
	}

	public void setData(Container<T> cont)
	{
		if (cont.getData() != data) {
			data = cont.getData();
			change(data);
		}
	}
}
