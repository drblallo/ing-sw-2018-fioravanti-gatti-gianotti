package progetto.view.gui;

import progetto.IClientController;
import progetto.model.IModel;

/**
 * This is the abstract class that is extended by javafx controllers that are aware of the controller
 * @author Federica
 */
public abstract class AbstractStateController {

	private StateManager stateManager;

	/**
	 *
	 * @param stateManager state manager to be set
	 */
	public void setStateManager(StateManager stateManager){
		this.stateManager = stateManager;
	}

	/**
	 *
	 * @return current state manager
	 */
	public StateManager getStateManager(){
		return stateManager;
	}

	/**
	 *
	 * @return current model
	 */
	public IModel getModel()
	{
		return stateManager.getModel();
	}

	/**
	 * controllers can implement this, it is called every time the associated FXML is called
	 */
	public void onPreShow(){
		//
	}

	/**
	 * classes can implement this, it is called the first time the associated FXML is called
	 */
	public void setup(){
		//
	}

	/**
	 * classes can implement this, it is called every time the observed game changes
	 */
	public void onGameChanged()
	{
		//nothing to do on default
	}

	/**
	 *
	 * @return current guiView
	 */
	public GUIView getView() {
		return stateManager.getGuiView();
	}

	/**
	 *
	 * @return current client controller
	 */
	public IClientController getController() {
		return getView().getController();
	}

	/**
	 *
	 * @return current chair of the user
	 */
	public int getChair()
	{
		return getController().getChair();
	}
}


