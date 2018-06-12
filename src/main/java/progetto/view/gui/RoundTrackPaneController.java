package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import progetto.model.Container;
import progetto.model.Dice;
import progetto.model.RoundTrackData;


public class RoundTrackPaneController extends AbstractController<RoundTrackData, Container<RoundTrackData>>{

    @FXML
    private HBox showingBox;
    @FXML
    private HBox roundBox;
    private RoundTrackData roundTrackData;
    private TextureDatabase textureDatabase;

    public void setup(){

        ImageView imageView;

        for(int i=0; i< RoundTrackData.NUMBER_OF_ROUNDS; i++){
            imageView = (ImageView) roundBox.getChildren().get(i);
            final int d = i;
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> showDices(d));
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> clearDices());
        }

        textureDatabase = TextureDatabase.getTextureDatabase();

    }

    @Override
    protected void update() {

        RoundTrackData newRoundTrackData = getObservable().getData();
        if(roundTrackData == newRoundTrackData || newRoundTrackData == null){
            return;
        }

        roundTrackData = newRoundTrackData;

        ImageView imageView;
        Dice dice;

        for(int i = 0; i<RoundTrackData.NUMBER_OF_ROUNDS; i++){
            if(!roundTrackData.isFree(i)&& roundTrackData.getDice(i,0)!=null) {
                    dice = roundTrackData.getDice(i, 0);
                    imageView = (ImageView) roundBox.getChildren().get(i);
                    imageView.setImage(textureDatabase.getDice(dice.getGameColor(), dice.getValue().ordinal() + 1));
                }
            }
    }

    @FXML
    private void showDices(int nRound){
        if(!roundTrackData.isFree(nRound)){
            int j=0;
            Dice dice;
            while (roundTrackData.getDice(nRound,j)!=null){
                dice = roundTrackData.getDice(nRound,j);
                showingBox.getChildren().add(new ImageView(textureDatabase
                        .getDice(dice.getGameColor(), dice.getValue().ordinal()+1)));
                j++;
            }
        }
    }

    @FXML
    private void clearDices(){
        showingBox.getChildren().clear();
    }
}
