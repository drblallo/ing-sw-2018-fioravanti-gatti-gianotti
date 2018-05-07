package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.ExtractedDices;

public class ExtractedDicesPaneController extends AbstractController<ExtractedDices, ExtractedDices> {

    @FXML
    private ListView listView;

    @Override
    protected void update() {

        listView.getItems().clear();

        for(int i=0; i<getObservable().getNumberOfDices(); i++){

            listView.getItems().add(new Label(getObservable().getDice(i).toString()));

        }

    }
}
