package progetto.integration.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ExistingGamesPaneController extends AbstractClientStateController{

    @FXML
    private ListView<String> listView;

    @FXML
    private Label errorLabel;

    @Override
    public void onPreShow() {

        listView.getItems().clear();

        for(int i = 0; i< getController().getConnectionCount(); i++)
        {

            listView.getItems().add(i + " " + getController().getNameOfConnection(i));

        }

    }

    @FXML
    private void onSelectButtonClicked(){


        if(listView.getSelectionModel().getSelectedItem()==null) {

            errorLabel.setText("Selezionare una connesione");
            return;

        }

        int clientGame = listView.getSelectionModel().getSelectedIndex();

        getController().setCurrentClientGame(clientGame);
        getViewStateMachine().getStateFromName("GamePane.fxml").show();

    }

    @FXML
    private void onBackButtonClicked(){

        getViewStateMachine().getStateFromName("StartingPane.fxml").show();

    }
}
