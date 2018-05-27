package progetto.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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

            pane = null;
            LOGGER.log(Level.SEVERE, e.getMessage());

        }

        scene = new Scene(pane);
        this.viewStateMachine = viewStateMachine;
        controller = fxmlLoader.<T>getController();
        controller.setViewStateMachine(viewStateMachine);
        controller.setup();

    }

    public String getFxmlName() {

        return fxmlName;

    }

    public T getController(){

        return controller;

    }

    public void show(){

        if(viewStateMachine.getCurrentViewState() == this){

            return;

        }
        controller.onPreShow();
        Stage stage = viewStateMachine.getStage();
        stage.setScene(scene);
        viewStateMachine.setCurrentViewState(this);
        stage.show();

    }

    protected void onHide(){

        //

    }

}
