package progetto.view.gui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewState<T extends AbstractStateController> {

    private static final Logger LOGGER = Logger.getLogger(ViewState.class.getName());
    private ViewStateMachine viewStateMachine;
    private Scene scene;
    private T controller;
    private String fxmlName;

    public ViewState(ViewStateMachine viewStateMachine, String fxml, Class c){

        fxmlName = fxml;
        viewStateMachine.addViewState(this);
        Pane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(fxmlName));
        try {

            pane = fxmlLoader.load();

        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "Failed to load {0}", e.getMessage());
            pane = null;
        }

        scene = new Scene(pane);
        this.viewStateMachine = viewStateMachine;
        controller = fxmlLoader.getController();
        controller.setViewStateMachine(viewStateMachine);

    }

    public String getFxmlName() {
        return fxmlName;
    }

    public T getController(){
        return controller;
    }

    public void show(boolean maximized){
        controller.onPreShow();
        Stage stage = viewStateMachine.getStage();
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
        viewStateMachine.setCurrentViewState(this);
        stage.setScene(scene);
        stage.show();
    }

    protected void onHide(){
        //
    }
}
