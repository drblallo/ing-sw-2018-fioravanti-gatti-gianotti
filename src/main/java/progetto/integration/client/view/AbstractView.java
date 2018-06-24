package progetto.integration.client.view;


import progetto.integration.client.IClientController;

/**
 * a view must extend this class to be able to interact properly with the controller.
 */
public abstract class AbstractView {

	private final IClientController controller;

	/**
	 * builds the new view, notifies the controller
	 * @param controller the controller this view will be attached to
	 */
	public AbstractView(IClientController controller)
	{
		this.controller = controller;
		controller.addView(this);
	}

	/**
	 * @param visible true if the controller wishes you to be visible, false otherwise
	 */
	public abstract void setVisible(boolean visible);

	/**
	 *
	 * @return the controller this view is attached too.
	 */
	public IClientController getController()
	{
		return controller;
	}

	/**
	 * method that is called every time the current game changes
	 */
	public abstract void onGameChanged();
}
