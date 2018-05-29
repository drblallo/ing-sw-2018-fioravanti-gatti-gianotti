package progetto.clientintegration;

import javafx.application.Application;
import javafx.stage.Stage;
import progetto.gui.*;
import progetto.serverintegration.ServerMain;

import java.io.IOException;


public class ClientWindow extends Application {

    public static void main(String[] args){

        ServerMain.main(args);
        launch(args);

    }

    @Override
    public synchronized void start(Stage primaryStage) throws IOException{

        primaryStage.setTitle("Client Window");

        primaryStage.setMaximized(true);
        ClientViewStateMachine clientViewStateMachine = new ClientViewStateMachine(primaryStage);
        clientViewStateMachine.setClientCommandProcessor(new ClientCommandProcessor());

        ViewState<StartingPaneController> startingPaneControllerViewState =
                new ViewState<>(clientViewStateMachine, "StartingPane.fxml", StartingPaneController.class);

        startingPaneControllerViewState.show();

        new ClientViewState<SocketRMIChoicePaneController>(clientViewStateMachine,
                "SocketRMIChoicePane.fxml", SocketRMIChoicePaneController.class);
        new ClientViewState<ExistingGamesPaneController>(clientViewStateMachine,
                "ExistingGamesPane.fxml", ExistingGamesPaneController.class);
        new ClientViewState<RoomsPaneController>(clientViewStateMachine,
                "RoomsPane.fxml", RoomsPaneController.class);
        new GameViewState(clientViewStateMachine, "GamePane.fxml", GamePaneController.class);


    }

}
