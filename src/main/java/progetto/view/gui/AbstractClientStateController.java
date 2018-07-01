package progetto.view.gui;

import progetto.IClientController;

/**
 * This is the abstract class that is extended by javafx controllers that are aware of the controller
 */
public abstract class AbstractClientStateController extends AbstractStateController {

    private IClientController controller;

    /**
     *
     * @param clientViewStateMachine the new current controller
     */
    public void setController(IClientController clientViewStateMachine){

        this.controller = clientViewStateMachine;

    }

    /**
     *
     * @return the current controller
     */
    public IClientController getController(){

        return controller;

    }

}


