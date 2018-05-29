package progetto.game;

import progetto.utils.AbstractObservable;

import java.io.Serializable;

public class DataContainer<T extends Serializable> extends AbstractObservable<T> implements IDataContainer<T>
{

	private T data;

	public DataContainer(T base)
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
