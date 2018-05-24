package progetto.clientmodelproxy;

import progetto.game.DataContainer;

import java.io.Serializable;

public class DataContainerProxy<T extends Serializable> extends DataContainer<T>
{
	public DataContainerProxy(T base)
	{
		super(base);
	}
}
