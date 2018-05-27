package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import progetto.game.DicePlacedFrameData;
import progetto.game.WindowFrame;

public class WindowFramePaneController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label favorToken;

    public void setup(WindowFrame windowFrame){

        favorToken.setText(Integer.toString(windowFrame.getFavorToken()));

        gridPane.getChildren().clear();

        for(int i = 0; i< DicePlacedFrameData.MAX_NUMBER_OF_ROWS-1; i++){

            for (int j=0; j<DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS-1; j++){

                gridPane.add(new Label(windowFrame.getColorBond(i,j).toString() +
                        windowFrame.getValueBond(i,j).toString()), i,j);

            }

        }

    }
}
