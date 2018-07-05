package progetto.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class State<T extends AbstractStateController> {

    private static final Logger LOGGER = Logger.getLogger(State.class.getName());
    private StateManager stateManager;
    private Scene scene;
    private T controller;
    private String fxmlName;

    public State(StateManager stateManager, String fxml, Class c){

        fxmlName = fxml;
        stateManager.addViewState(this);
        Pane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(fxmlName));
        try {

            pane = fxmlLoader.load();

        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "Failed to load {0}", e.getMessage());
            pane = null;
        }

        scene = new Scene(pane);
        this.stateManager = stateManager;
        controller = fxmlLoader.getController();
        controller.setStateManager(stateManager);
        getController().setup();

    }

    public void onGameChanged()
    {
        controller.onGameChanged();
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public T getController(){
        return controller;
    }

    public void show(boolean maximized){
        controller.onPreShow();
        Stage stage = stateManager.getStage();
        if (maximized) {
            Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
            stage.setX(rectangle2D.getMinX());
            stage.setY(rectangle2D.getMinY());
            stage.setWidth(rectangle2D.getWidth());
            stage.setHeight(rectangle2D.getHeight());
        }
        else {
            stage.sizeToScene();
            stage.centerOnScreen();
        }
		stateManager.setCurrentState(this);
        stage.setScene(scene);
        stage.show();
    }

    protected void onHide(){
        //
    }
}
