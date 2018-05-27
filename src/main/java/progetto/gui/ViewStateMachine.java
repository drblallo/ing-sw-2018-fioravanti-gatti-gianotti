package progetto.gui;

import javafx.stage.Stage;
import progetto.game.Game;
import progetto.game.IExecuibleGame;

import java.util.HashMap;

public class ViewStateMachine {

    private Stage stage;
    private ViewState currentViewState;
    private HashMap<String, ViewState> stateHashMap = new HashMap<>();
    private IExecuibleGame currentGame;

    public IExecuibleGame getCurrentGame() {

        return currentGame;

    }

    public void setCurrentGame(IExecuibleGame currentGame) {

        this.currentGame = currentGame;

    }

    public ViewStateMachine(Stage stage){

        this.stage = stage;

    }

    public Stage getStage() {

        return stage;

    }

    public ViewState getCurrentViewState() {

        return currentViewState;

    }

    void setCurrentViewState(ViewState currentViewState){

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
}
