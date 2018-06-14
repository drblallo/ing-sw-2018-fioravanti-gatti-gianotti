package progetto.integration.client.view;

import progetto.integration.client.IClientController;
import progetto.view.gui.AbstractStateController;

public abstract class AbstractClientStateController extends AbstractStateController {

    private IClientController controller;

    public void setController(IClientController clientViewStateMachine){

        this.controller = clientViewStateMachine;

    }

    public IClientController getController(){

        return controller;

    }

}


