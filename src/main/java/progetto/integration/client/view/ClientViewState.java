package progetto.integration.client.view;

import progetto.integration.client.view.cl.AbstractCLViewState;
import progetto.view.gui.ViewState;

public class ClientViewState<T extends AbstractClientStateController> extends ViewState<T> {

    public ClientViewState(GUIView view, String fxml, Class c) {

        super(view.getViewStateMachine(), fxml, c);
        getController().setController(view.getController());
    }

    public void setState(AbstractCLViewState abstractCLViewState){
        //Set the state
    }

}
