package progetto.clientintegration;

import javafx.stage.Stage;
import progetto.gui.ViewStateMachine;

public class ClientViewStateMachine extends ViewStateMachine {

    public ClientViewStateMachine(Stage stage) {

        super(stage);

    }

    private ClientCommandProcessor clientCommandProcessor;
    private ClientGame currentClientGame;

    public void setCurrentClientGame(ClientGame clientGame){

        this.currentClientGame = clientGame;
        setCurrentGame(clientGame);
        getClientCommandProcessor().setGame(clientGame);

    }

    public ClientGame getCurrentClientGame() {

        return currentClientGame;

    }

    public void setClientCommandProcessor(ClientCommandProcessor clientCommandProcessor){

        this.clientCommandProcessor = clientCommandProcessor;

    }

    public ClientCommandProcessor getClientCommandProcessor() {

        return clientCommandProcessor;

    }
}
