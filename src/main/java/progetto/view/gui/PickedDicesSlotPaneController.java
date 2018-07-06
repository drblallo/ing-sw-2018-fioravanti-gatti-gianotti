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

/**
 * this is the class that handles the picked dices slot fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class PickedDicesSlotPaneController extends AbstractController{

    @FXML
    private TilePane tilePane;
    private static final int DICE_DIMENSION = 55;
    private int numberOfPlayerboard;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     * @param i the playerboard associated to this controller
     */
    public void setUp(GUIView view, int i){
    	super.setUp(view);
        numberOfPlayerboard = i;
        view.getController().getObservable().getPlayerBoard(i).getPickedDicesSlot()
                .addObserver(ogg -> Platform.runLater(this::update));
    }

    /**
     *called when the picked dices of the player board associated change
     * show the current picked dices of the player board
     */
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


    /**
     * called when the user clicks on a dice among the ones in the picked dices slot
     * if possible send a ToolCardSetPickedDiceAction to the controller
     * @param event click on the dice
     * @param i number of the dice associated to the event
     */
    private void onMouseClicked(MouseEvent event, int i){
        ToolCardSetPickedDiceAction toolCardSetPickedDiceAction = new ToolCardSetPickedDiceAction(
                getController().getChair(), i);
        if (toolCardSetPickedDiceAction.canBeExecuted(getController().getModel()))
            getController().sendAction(toolCardSetPickedDiceAction);

        event.consume();
    }

    /**
     * called when the user starts a drag on a dice
     * @param event starting drag the dice
     * @param imageView imageView containing the image of the dice associated to the event
     * @param toTransfer string containing the number of the dice associated to the event
     */
    private void onDragDetected(MouseEvent event, ImageView imageView, String toTransfer){

        Dragboard dragboard = imageView.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(toTransfer);
        content.putImage(imageView.getImage());
        dragboard.setContent(content);

        event.consume();
    }
}
