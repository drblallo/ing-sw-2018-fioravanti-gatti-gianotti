package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.model.Container;
import progetto.model.ExtractedDicesData;

public class ExtractedDicesPaneController extends AbstractController<ExtractedDicesData,
		Container<ExtractedDicesData>> {

    @FXML
    private ListView listView;

    @Override
    protected void update() {

        ExtractedDicesData extractedDicesData = getObservable().getData();

        listView.getItems().clear();

        for(int i=0; i<extractedDicesData.getNumberOfDices(); i++){

            listView.getItems().add(new Label(extractedDicesData.getDice(i).toString()));

        }

    }
}
