package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;
import progetto.controller.ToolCardSetPickedDiceAction;
import progetto.model.Dice;
import progetto.model.PickedDicesSlotData;


public class PickedDicesSlotPaneController extends AbstractController{

    @FXML
    private TilePane tilePane;
    private static final int DICE_DIMENSION = 55;
    private int numberOfPlayerboard;

    public void setup(GUIView view, int i){
    	super.setUp(view);
        numberOfPlayerboard = i;
        view.getController().getObservable().getPlayerBoard(i).getPickedDicesSlot()
                .addObserver(ogg -> Platform.runLater(this::update));
    }

    private void update() {

        PickedDicesSlotData pickedDicesSlotData =
                getController().getModel().getPlayerBoard(numberOfPlayerboard).getPickedDicesSlot().getData();
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
            final int j = i;
            imageView.setOnDragDetected(event -> onDragDetected(event, imageView, toTransfer));
            imageView.setOnMouseClicked(event -> onMouseClicked(event, j));

            tilePane.getChildren().add(imageView);
        }
    }


    private void onMouseClicked(MouseEvent event, int i){
        ToolCardSetPickedDiceAction toolCardSetPickedDiceAction = new ToolCardSetPickedDiceAction(
                getController().getChair(), i);
        if (toolCardSetPickedDiceAction.canBeExecuted(getController().getModel()))
            getController().sendAction(toolCardSetPickedDiceAction);

        event.consume();
    }

    private void onDragDetected(MouseEvent event, ImageView imageView, String toTransfer){

        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(toTransfer);
        content.putImage(imageView.getImage());
        dragboard.setContent(content);

        event.consume();
    }
}
