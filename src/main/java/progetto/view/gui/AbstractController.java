package progetto.view.gui;

import progetto.IClientController;
import progetto.model.IModel;

/**
 * @author Federica
 */
public abstract class AbstractController {

	private GUIView view;

	/**
	 *
	 * @return the current gui view
	 */
	public GUIView getView() {
		return view;
	}

	/**
	 * setUp the controller
	 * @param view the current gui view
	 */
	public void setUp(GUIView view)
	{
		this.view = view;
	}

	/**
	 *
	 * @return the client controller
	 */
	public IClientController getController()
	{
		return view.getController();
	}

	/**
	 *
	 * @return the current model
	 */
	public IModel getModel()
	{
		return getController().getModel();
	}

	/**
	 *
	 * @return number of the chair of the player
	 */
	public int getChair()
	{
		return getController().getChair();
	}
}
