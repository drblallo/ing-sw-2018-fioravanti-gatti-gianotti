package progetto.integration.client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ExistingGamesPaneController extends AbstractClientStateController{

    @FXML
    private ListView<String> listView;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane anchorPane;
    private static final int BACKGROUND_SIZE = 300;

    @Override
    public void onPreShow() {
        listView.getItems().clear();
        for(int i = 0; i< getController().getConnectionCount(); i++)
        {
            listView.getItems().add(i + " " + getController().getNameOfConnection(i));
        }
        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(BACKGROUND_SIZE,BACKGROUND_SIZE,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);

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
