package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import progetto.game.DicePlacedFrame;

public class DicePlacedFramePaneController extends AbstractController <DicePlacedFrame, DicePlacedFrame> {

    @FXML
    private Label number;

    @FXML
    private GridPane gridPane;

    @Override
    protected void update() {

        number.setText(Integer.toString(getObservable().getNDices()));

        gridPane.getChildren().clear();

        for(int i=0; i<3; i++){

            for (int j=0; j<4; j++){

                if(getObservable().getDice(i,j)!=null){

                    gridPane.add(new Label(getObservable().getDice(i,j).toString()),i,j);

                }

            }

        }
    }
}
