package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.model.Container;
import progetto.model.Dice;
import progetto.model.ExtractedDicesData;

public class ExtractedDicesPaneController extends AbstractController<ExtractedDicesData,
		Container<ExtractedDicesData>> {

    @FXML
    private TilePane tilePane;

    @Override
    protected void update() {

        ExtractedDicesData extractedDicesData = getObservable().getData();
        tilePane.getChildren().clear();
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        Dice dice;
        for(int i=0; i<extractedDicesData.getNumberOfDices(); i++){
            dice = extractedDicesData.getDice(i);
            tilePane.getChildren().add(new ImageView(textureDatabase
                    .getDice(dice.getGameColor(), dice.getValue().ordinal()+1)));
        }

    }
}
