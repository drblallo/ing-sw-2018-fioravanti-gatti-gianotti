package progetto.integration.client.view;

import progetto.integration.client.ClientController;
import progetto.view.gui.AbstractStateController;

public abstract class AbstractClientStateController extends AbstractStateController {

    private ClientController controller;

    public void setController(ClientController clientViewStateMachine){

        this.controller = clientViewStateMachine;

    }

    public ClientController getController(){

        return controller;

    }

}


