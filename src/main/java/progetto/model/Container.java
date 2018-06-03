package progetto.model;

import progetto.utils.AbstractObservable;

import java.io.Serializable;

public class Container<T extends Serializable> extends AbstractObservable<T>
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
		this.data = data;
		change(data);
	}

}
