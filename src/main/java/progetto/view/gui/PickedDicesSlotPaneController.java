package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.model.Container;
import progetto.model.Dice;
import progetto.model.PickedDicesSlotData;

public class PickedDicesSlotPaneController extends AbstractController <PickedDicesSlotData,
		Container<PickedDicesSlotData>> {

    @FXML
    private TilePane tilePane;


    @Override
    protected void update() {

        PickedDicesSlotData pickedDicesSlotData = getObservable().getData();
        Dice dice;

        tilePane.getChildren().clear();
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();

        for(int i=0; i<pickedDicesSlotData.getNDices(); i++){

            dice = pickedDicesSlotData.getDicePlacementCondition(i).getDice();

            tilePane.getChildren().add(new ImageView(textureDatabase.getDice(dice.getGameColor(),
                    dice.getValue().ordinal()+1)));
        }
    }
}
