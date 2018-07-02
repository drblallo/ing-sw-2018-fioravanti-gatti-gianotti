package progetto.view.gui;

import progetto.IClientController;
import progetto.model.IModel;

public abstract class AbstractController {
	private GUIView view;

	public GUIView getView() {
		return view;
	}

	public void setUp(GUIView view)
	{
		this.view = view;
	}

	public IClientController getController()
	{
		return view.getController();
	}

	public IModel getModel()
	{
		return getController().getModel();
	}

	public int getChair()
	{
		return getController().getChair();
	}
}
