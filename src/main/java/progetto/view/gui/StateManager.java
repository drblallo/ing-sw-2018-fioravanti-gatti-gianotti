package progetto.view.gui;

import javafx.stage.Stage;
import progetto.IClientController;
import progetto.controller.IGameController;
import progetto.model.IModel;
import progetto.model.ObservableModel;

import java.util.HashMap;

/**
 * class that manages FSM states
 */
public class StateManager {

    private Stage stage;
    private State currentState;
    private HashMap<String, State> stateHashMap = new HashMap<>();
    private GUIView guiView;

    /**
     * public constructor
     * @param stage stage to be set
     * @param guiView current guiView
     */
    public StateManager(Stage stage, GUIView guiView){
        this.stage = stage;
        this.guiView = guiView;
    }

    /**
     *
     * @return current guiView
     */
    public GUIView getGuiView() {
        return guiView;
    }

    /**
     *
     * @return current stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Set the new currentState
     * @param currentState state to be set
      */
    void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    /**
     * called onGameChanged on every state in the stateManager
     */
    public void onGameChanged(){
        for (State s: stateHashMap.values()) {
                s.onGameChanged();
            }
    }

    /**
     * Add a new state to this stateManager
     * @param state state to be added
     */
    public void addViewState(State state){
        stateHashMap.put(state.getFxmlName(), state);
    }

    /**
     * Return a state of the stateManager from his name
     * @param nameState name of the needed state
     * @param <T> type of the controller that handles the needed state
     * @return needed state
     */
    public <T extends AbstractStateController> State<T> getStateFromName(String nameState) {
        if (stateHashMap.containsKey(nameState)) {
            return (State<T>) stateHashMap.get(nameState);
        }
        return null;
    }

    /**
     *
     * @return current client controller
     */
    public IClientController getClientController(){
        return guiView.getController();
    }

    /**
     *
     * @return current observable model
     */
    public ObservableModel getObsModel()
    {
        return getClientController().getObservable();
    }

    /**
     *
     * @return current immutable model
     */
    public IModel getModel()
    {
        return  getClientController().getModel();
    }
}
