package progetto.clientintegration;

import progetto.gui.ViewState;

public class ClientViewState<T extends AbstractClientStateController> extends ViewState<T> {

    private final ClientViewStateMachine clientViewStateMachine;

    public ClientViewState(ClientViewStateMachine clientViewStateMachine, String fxml, Class c) {

        super(clientViewStateMachine, fxml, c);
        this.clientViewStateMachine = clientViewStateMachine;
        getController().setClientViewStateMachine(clientViewStateMachine);
    }

    public ClientViewStateMachine getClientViewStateMachine() {

        return clientViewStateMachine;

    }
}
