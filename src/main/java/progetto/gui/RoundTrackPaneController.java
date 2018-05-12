package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.RoundTrack;
import progetto.game.RoundTrackData;

public class RoundTrackPaneController extends AbstractController<RoundTrackData, RoundTrack> {

    @FXML
    private ListView<Label> listView;

    private RoundTrackData roundTrackData;

    @Override
    protected void update() {

        int j=0;

        RoundTrackData newRoundTrackData = getObservable().getRoundTrackData();

        if(newRoundTrackData == roundTrackData){

            return;

        }

        roundTrackData = newRoundTrackData;

        listView.getItems().clear();

        for(int i=0; i<RoundTrackData.NUMBER_OF_ROUNDS; i++){

            if(!roundTrackData.isFree(i)){

                j=0;

                while (roundTrackData.getDice(i, j)!=null){

                    listView.getItems().add(new Label(roundTrackData.getDice(i,j).toString() + " Turn: " + i));
                    j++;

                }

            }

        }

    }
}
