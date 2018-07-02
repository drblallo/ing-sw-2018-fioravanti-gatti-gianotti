package progetto.view.gui;

import javafx.stage.Stage;
import progetto.IClientController;
import progetto.controller.IGameController;
import progetto.model.IModel;
import progetto.model.ObservableModel;

import java.util.HashMap;

public class StateManager {

    private Stage stage;
    private State currentState;
    private HashMap<String, State> stateHashMap = new HashMap<>();
    private IGameController currentGame;
    private GUIView guiView;

    public StateManager(Stage stage, GUIView guiView){
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

    void setCurrentState(State currentState){
        if(this.currentState !=null){
            this.currentState.onHide();
        }
        this.currentState = currentState;

    }

    public void addViewState(State state){
        stateHashMap.put(state.getFxmlName(), state);
    }

    public <T extends AbstractStateController> State<T> getStateFromName(String nameState) {
        if (stateHashMap.containsKey(nameState)) {
            return (State<T>) stateHashMap.get(nameState);
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
