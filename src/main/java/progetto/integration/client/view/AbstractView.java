package progetto.integration.client.view;


import progetto.integration.client.IClientController;

public abstract class AbstractView {

	private final IClientController controller;

	public AbstractView(IClientController controller)
	{
		this.controller = controller;
		controller.addView(this);
	}

	public abstract void setVisible(boolean visible);

	public IClientController getController()
	{
		return controller;
	}

	public abstract void onGameChanged();
}
