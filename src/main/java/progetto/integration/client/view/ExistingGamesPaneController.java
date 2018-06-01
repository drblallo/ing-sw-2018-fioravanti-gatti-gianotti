package progetto.integration.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.integration.client.ClientGame;

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

        clientGameList = getController().getGames();

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
                getController().getGames().get(listView.getSelectionModel().getSelectedIndex());

        getController().setCurrentClientGame(clientGame);
        getViewStateMachine().getStateFromName("GamePane.fxml").show();

    }

    @FXML
    private void onBackButtonClicked(){

        getViewStateMachine().getStateFromName("StartingPane.fxml").show();

    }
}
