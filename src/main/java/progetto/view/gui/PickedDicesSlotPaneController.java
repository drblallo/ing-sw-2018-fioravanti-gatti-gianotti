package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;
import progetto.integration.client.view.GUIView;
import progetto.model.Dice;
import progetto.model.PickedDicesSlotData;


public class PickedDicesSlotPaneController {

    @FXML
    private TilePane tilePane;
    private static final int DICE_DIMENSION = 55;
    private GUIView view;
    private int numberOfPlayerboard;

    public void setup(GUIView view, int i){
        this.view = view;
        numberOfPlayerboard = i;
        view.getController().getObservable().getPlayerBoard(i).getPickedDicesSlot()
                .addObserver(ogg -> Platform.runLater(this::update));
    }

    private void update() {

        PickedDicesSlotData pickedDicesSlotData =
                view.getController().getModel().getPlayerBoard(numberOfPlayerboard).getPickedDicesSlot().getData();
        Dice dice;

        tilePane.getChildren().clear();
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();

        for(int i=0; i<pickedDicesSlotData.getNDices(); i++){

            dice = pickedDicesSlotData.getDicePlacementCondition(i).getDice();
            ImageView imageView = new ImageView(textureDatabase.getDice(dice.getGameColor(),
                    dice.getValue().ordinal()+1));
            imageView.setFitWidth(DICE_DIMENSION);
            imageView.setFitHeight(DICE_DIMENSION);
            final String toTransfer = "" + i;
            imageView.setOnDragDetected(event -> {
                Dragboard dragboard = imageView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(toTransfer);
                content.putImage(imageView.getImage());
                dragboard.setContent(content);

                event.consume();
            });

            tilePane.getChildren().add(imageView);
        }
    }
}
