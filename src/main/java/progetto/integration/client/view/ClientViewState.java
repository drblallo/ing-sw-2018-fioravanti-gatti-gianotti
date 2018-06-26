package progetto.integration.client.view;

import progetto.view.gui.ViewState;

public class ClientViewState<T extends AbstractClientStateController> extends ViewState<T> {

    public ClientViewState(GUIView view, String fxml, Class c) {

        super(view.getViewStateMachine(), fxml, c);
        getController().setController(view.getController());

    }


}
