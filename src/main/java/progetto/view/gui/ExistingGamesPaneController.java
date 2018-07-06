package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * this is the class that handles the existing game fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class ExistingGamesPaneController extends AbstractStateController {

    @FXML
    private ListView<String> listView;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane anchorPane;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     */
    @Override
    public void setup(){
        Image image = new Image(getClass().getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(
                Control.USE_COMPUTED_SIZE,
                Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
    }

    /**
     * called every time this window is displayed
     * load the existing connections
     */
    @Override
    public void onPreShow() {
        listView.getItems().clear();
        for(int i = 0; i< getController().getConnectionCount(); i++)
        {
            listView.getItems().add(i + " " + getController().getNameOfConnection(i));
        }
    }

    /**
     * called when selectButton is clicked
     * set the selected connection as the current connection
     */
    @FXML
    private void onSelectButtonClicked(){
        if(listView.getSelectionModel().getSelectedItem()==null) {
            errorLabel.setText("Selezionare una connesione");
            return;
        }
        int clientGame = listView.getSelectionModel().getSelectedIndex();
        getController().setCurrentClientGame(clientGame);
        getStateManager().getStateFromName("GamePane.fxml").show(true);
    }

    /**
     * called when backButton is clicked
     * change the scene to Starting Pane
     */
    @FXML
    private void onBackButtonClicked(){
        getStateManager().getStateFromName("StartingPane.fxml").show(false);
    }
}
