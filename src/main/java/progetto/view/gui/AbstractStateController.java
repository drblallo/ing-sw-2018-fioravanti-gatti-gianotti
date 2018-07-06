package progetto.view.gui;

import progetto.IClientController;
import progetto.model.IModel;

/**
 * This is the abstract class that is extended by javafx controllers that are aware of the controller
 * @author Federica
 */
public abstract class AbstractStateController {

	private StateManager stateManager;

	public void setStateManager(StateManager stateManager){
		this.stateManager = stateManager;
	}

	public StateManager getStateManager(){
		return stateManager;
	}

	public IModel getModel()
	{
		return stateManager.getModel();
	}

	public void onPreShow(){
		//
	}

	public void setup(){
		//
	}

	public void onGameChanged()
	{
		//nothing to do on default
	}

	public GUIView getView() {
		return stateManager.getGuiView();
	}

	public IClientController getController() {
		return getView().getController();
	}

	public int getChair()
	{
		return getController().getChair();
	}
}


