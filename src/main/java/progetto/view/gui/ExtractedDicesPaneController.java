package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.controller.PickDiceAction;
import progetto.controller.ToolCardSetSinglePlayerDiceAction;
import progetto.model.Dice;
import progetto.model.ExtractedDicesData;

/**
 * this is the class that handles the extracted dices fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class ExtractedDicesPaneController extends AbstractController{

    @FXML
    private TilePane tilePane;
    private static final int DICE_DIMENSION = 55;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     */
    @Override
    public void setUp(GUIView view){
    	super.setUp(view);
        view.getController().getObservable().getMainBoard().getExtractedDices()
                .addObserver(ogg -> Platform.runLater(this::update));
    }

    /**
     * displayes all the extracted dices
     */
    private void update() {
        ExtractedDicesData extractedDicesData = getModel().getMainBoard().getExtractedDices().getData();
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
                getController().sendAction(new PickDiceAction(getChair(), d));
                getController().sendAction(new ToolCardSetSinglePlayerDiceAction(getChair(), d));
            });
            tilePane.getChildren().add(imageView);
        }
    }
}
