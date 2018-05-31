package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.model.DataContainer;
import progetto.model.RoundTrackData;

public class RoundTrackPaneController extends AbstractController<RoundTrackData, DataContainer<RoundTrackData>> {

    @FXML
    private ListView<Label> listView;

    @Override
    protected void update() {

        int j;

        RoundTrackData roundTrackData = getObservable().getData();

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
