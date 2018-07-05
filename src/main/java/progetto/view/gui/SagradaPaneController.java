package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SagradaPaneController extends AbstractStateController{

    @FXML
    private ImageView sagradaImageView;

    @FXML
    public void initialize(){
        sagradaImageView.setImage(new Image(getClass().getResourceAsStream("sagrada-header.jpg")));
    }

    @FXML
    private void startGame(){
        getStateManager().getStateFromName("StartingPane.fxml").show(false);
    }

}
