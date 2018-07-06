package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * this is the class that handles the sagrada pane fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class SagradaPaneController extends AbstractStateController{

    @FXML
    private ImageView sagradaImageView;

    /**
     * called when the FXML is loaded
     * load the background image
     */
    @FXML
    public void initialize(){
        sagradaImageView.setImage(new Image(getClass().getResourceAsStream("sagrada-header.jpg")));
    }

    /**
     * called when the sagradaImageView is clicked
     * change the scene to StartingPane
     */
    @FXML
    private void startGame(){
        getStateManager().getStateFromName("StartingPane.fxml").show(false);
    }

}
