package progetto.clientintegration;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import progetto.gui.AbstractController;
import progetto.gui.AbstractStateController;

import java.util.List;

public class ExistingGamesPaneController extends AbstractClientStateController{

    @FXML
    private ListView<String> listView;

    @FXML
    private Label errorLabel;

    @Override
    public void onPreShow() {

        listView.getItems().clear();

        List<ClientGame> clientGameList;

        clientGameList = ExistingGames.getExistingGames().getExistingGamesList();

        for(int i = 0; i< clientGameList.size(); i++)
        {

            listView.getItems().add(i + " " + clientGameList.get(i).getClientConnection().getRoom().getRoomName());

        }

    }

    @FXML
    private void onSelectButtonClicked(){


        if(listView.getSelectionModel().getSelectedItem()==null) {

            errorLabel.setText("Selezionare una connesione");
            return;

        }

        ClientGame clientGame =
                ExistingGames.getExistingGames().getExistingGamesList().get(listView.getSelectionModel().getSelectedIndex());

        getClientViewStateMachine().setCurrentClientGame(clientGame);
        getViewStateMachine().getStateFromName("GamePane.fxml").show();

    }

    @FXML
    private void onBackButtonClicked(){

        getViewStateMachine().getStateFromName("StartingPane.fxml").show();

    }
}
