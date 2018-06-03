package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import progetto.model.Container;
import progetto.model.DicePlacedFrameData;

public class DicePlacedFramePaneController extends AbstractController <DicePlacedFrameData,
		Container<DicePlacedFrameData>> {

    @FXML
    private Label number;

    @FXML
    private GridPane gridPane;

    @Override
    protected void update() {

        DicePlacedFrameData dicePlacedFrameData = getObservable().getData();

        number.setText(Integer.toString(dicePlacedFrameData.getNDices()));

        gridPane.getChildren().clear();

        for(int i=0; i<DicePlacedFrameData.MAX_NUMBER_OF_ROWS; i++){

            for (int j=0; j<DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; j++){

                if(dicePlacedFrameData.getDice(j,i)!=null){

                    gridPane.add(new Label(dicePlacedFrameData.getDice(j,i).toString()),j,i);

                }

            }

        }
    }
}
