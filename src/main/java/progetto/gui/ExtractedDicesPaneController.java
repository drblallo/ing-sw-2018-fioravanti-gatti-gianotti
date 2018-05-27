package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.ExtractedDices;
import progetto.game.ExtractedDicesData;

public class ExtractedDicesPaneController extends AbstractController<ExtractedDicesData, ExtractedDices> {

    @FXML
    private ListView listView;

    @Override
    protected void update() {

        ExtractedDicesData extractedDicesData = getObservable().getExtractedDicesData();

        listView.getItems().clear();

        for(int i=0; i<extractedDicesData.getNumberOfDices(); i++){

            listView.getItems().add(new Label(extractedDicesData.getDice(i).toString()));

        }

    }
}
