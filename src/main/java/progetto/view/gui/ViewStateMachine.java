package progetto.view.gui;

import javafx.stage.Stage;
import progetto.IClientController;
import progetto.controller.IGameController;
import progetto.model.IModel;
import progetto.model.ObservableModel;

import java.util.HashMap;

public class ViewStateMachine {

    private Stage stage;
    private ViewState currentViewState;
    private HashMap<String, ViewState> stateHashMap = new HashMap<>();
    private IGameController currentGame;
    private GUIView guiView;

    public ViewStateMachine(Stage stage, GUIView guiView){
        this.stage = stage;
        this.guiView = guiView;
    }

    public GUIView getGuiView() {
        return guiView;
    }

    public IGameController getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(IGameController currentGame) {
        this.currentGame = currentGame;
    }

    public Stage getStage() {
        return stage;
    }

    void setCurrentViewState(ViewState currentViewState){
        if(this.currentViewState!=null){
            this.currentViewState.onHide();
        }
        this.currentViewState = currentViewState;

    }

    public void addViewState(ViewState viewState){
        stateHashMap.put(viewState.getFxmlName(), viewState);
    }

    public <T extends AbstractStateController> ViewState<T> getStateFromName(String nameState) {
        if (stateHashMap.containsKey(nameState)) {
            return (ViewState<T>) stateHashMap.get(nameState);
        }
        return null;
    }

    public IClientController getClientController(){
        return guiView.getController();
    }


    public ObservableModel getObsModel()
    {
        return getClientController().getObservable();
    }

    public IModel getModel()
    {
        return  getClientController().getModel();
    }
}
