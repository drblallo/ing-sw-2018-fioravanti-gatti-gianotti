package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.controller.PickDiceAction;
import progetto.controller.ToolCardSetSinglePlayerDiceAction;
import progetto.model.Dice;
import progetto.model.ExtractedDicesData;

public class ExtractedDicesPaneController {

    @FXML
    private TilePane tilePane;
    private static final int DICE_DIMENSION = 55;
    private GUIView view;

    public void setup(GUIView view){
        this.view = view;
        view.getController().getObservable().getMainBoard().getExtractedDices()
                .addObserver(ogg -> Platform.runLater(this::update));
    }

    private void update() {
        ExtractedDicesData extractedDicesData = view.getController().getModel()
                .getMainBoard().getExtractedDices().getData();
        tilePane.getChildren().clear();
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        Dice dice;
        for(int i=0; i<extractedDicesData.getNumberOfDices(); i++){
            dice = extractedDicesData.getDice(i);
            ImageView imageView = new ImageView(textureDatabase
                    .getDice(dice.getGameColor(), dice.getValue().ordinal()+1));
            imageView.setFitHeight(DICE_DIMENSION);
            imageView.setFitWidth(DICE_DIMENSION);
            final int d = i;
            imageView.setOnMouseClicked(event -> {
                view.getController().sendAction
                        (new PickDiceAction(view.getController().getChair(), d));
                view.getController().sendAction
                        (new ToolCardSetSinglePlayerDiceAction(view.getController().getChair(), d));
            });
            tilePane.getChildren().add(imageView);
        }
    }
}
