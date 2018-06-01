package progetto.integration.client.view;


import progetto.integration.client.ClientController;

public abstract class AbstractView {

	private final ClientController controller;

	public AbstractView(ClientController controller)
	{
		this.controller = controller;
		controller.addView(this);
	}

	public abstract void setVisible(boolean visible);

	public ClientController getController()
	{
		return controller;
	}

	public abstract void onGameChanged();
}
