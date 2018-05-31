package progetto.integration.client.view;

import progetto.integration.client.ClientController;
import progetto.view.gui.AbstractStateController;

public abstract class AbstractClientStateController extends AbstractStateController {

    private ClientController clientViewStateMachine;

    public void setController(ClientController clientViewStateMachine){

        this.clientViewStateMachine = clientViewStateMachine;

    }

    public ClientController getController(){

        return clientViewStateMachine;

    }

}


