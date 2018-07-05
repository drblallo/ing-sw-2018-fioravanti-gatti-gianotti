package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import progetto.controller.ToolCardSetDiceRoundTrackAction;
import progetto.model.Dice;
import progetto.model.RoundTrackData;

/**
 * this is the class that handles the round track fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
public class RoundTrackPaneController extends AbstractController{

    @FXML
    private HBox showingBox;
    @FXML
    private HBox roundBox;
    @FXML
    private VBox vBox;
    private TextureDatabase textureDatabase;
    private static final int DICE_DIMENSION = 55;

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     */
    @Override
    public void setUp(GUIView view){

    	super.setUp(view);
        view.getController().getObservable().getRoundTrack().addObserver(ogg -> update());

        ImageView imageView;
        for(int i=0; i< RoundTrackData.NUMBER_OF_ROUNDS; i++){
            imageView = (ImageView) roundBox.getChildren().get(i);
            final int d = i;
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> showDices(d));
        }

        textureDatabase = TextureDatabase.getTextureDatabase();
        vBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> clearDices());
    }

    /**
     * called when round track changes
     * update dices in the round track
     */
    private void update() {

        RoundTrackData roundTrackData = getView().getStateManager().getModel().getRoundTrack().getData();
        ImageView imageView;
        Dice dice;

        for(int i = 0; i<RoundTrackData.NUMBER_OF_ROUNDS; i++){
            imageView = (ImageView) roundBox.getChildren().get(i);
            if(!roundTrackData.isFree(i)) {
                dice = roundTrackData.getDice(i, 0);
                if (imageView.getImage()!=textureDatabase.getDice(dice.getGameColor(), dice.getValue().ordinal()+1))
                    imageView.setImage(textureDatabase.getDice(dice.getGameColor(), dice.getValue().ordinal() + 1));
            }
            else if (imageView.getImage() != textureDatabase.getDice(null, -1))
                imageView.setImage(textureDatabase.getDice(null, -1));
        }
    }

    /**
     * called when the mouse enters in a node representing a past round
     * show all the dices of the selected round
     * @param nRound
     */
    @FXML
    private void showDices(int nRound){
        clearDices();
        RoundTrackData roundTrackData = getModel().getRoundTrack().getData();
        if(!roundTrackData.isFree(nRound)){
            int j=0;
            Dice dice;
            while (roundTrackData.getDice(nRound,j)!=null){
                dice = roundTrackData.getDice(nRound,j);
                ImageView imageView = new ImageView(textureDatabase.getDice(dice.getGameColor(),
                        dice.getValue().ordinal()+1));
                imageView.setFitHeight(DICE_DIMENSION);
                imageView.setFitWidth(DICE_DIMENSION);
                final int i = j;

                imageView.setOnMouseClicked(event -> {
                    ToolCardSetDiceRoundTrackAction toolCardSetDiceRoundTrackAction =
                            new ToolCardSetDiceRoundTrackAction(getChair(), nRound, i);
                    if(toolCardSetDiceRoundTrackAction.canBeExecuted(getModel()))
                        getController().sendAction(toolCardSetDiceRoundTrackAction);
                });
                showingBox.getChildren().add(imageView);
                j++;
            }
        }
    }

    /**
     * called when the mouse exits from round track area
     * clears the extra dices showed
     */
    @FXML
    private void clearDices(){
        showingBox.getChildren().clear();
    }
}
