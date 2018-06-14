package progetto.view.gui;

import progetto.model.Container;
import progetto.utils.AbstractObservable;
import progetto.utils.Callback;

import java.io.Serializable;

public class ComposableController<U extends Serializable, T extends Container<U>> extends AbstractController<U, T>
{
	private Callback<U> onModifiedCallback = new Callback<>();

	@Override
	protected void update() {
		onModifiedCallback.call(getObservable().getData());
	}


	public Callback<U> getOnModifiedCallback() {
		return onModifiedCallback;
	}

	public U getLastData()
	{
		return getObservable().getData();
	}
}
