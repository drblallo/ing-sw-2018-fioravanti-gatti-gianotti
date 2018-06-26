package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import progetto.model.MainBoardData;

public class ObjectiveCardsController {

    @FXML
    private HBox objectiveCardsContainer;

    private void setup(MainBoardData mainBoardData){

        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();

        for (int i = 0; i<mainBoardData.getPublicObjectiveCards().size(); i++){
            //COME LE CARICO SE GLI OBIETTIVI NON HANNO QUALOCOSA DI IDENTIFICATIVO?
            ImageView imageView = new ImageView(textureDatabase.getPublicObjective(0));
        }
    }
}
