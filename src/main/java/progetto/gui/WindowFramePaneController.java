package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import progetto.game.WindowFrame;

public class WindowFramePaneController extends AbstractController<WindowFrame, WindowFrame> {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label favorToken;

    @Override
    protected void update() {

        favorToken.setText(Integer.toString(getObservable().getFavorToken()));

        gridPane.getChildren().clear();

        for(int i=0; i<3; i++){

            for (int j=0; j<4; j++){

                gridPane.add(new Label(getObservable().getColorBond(i,j).toString() + getObservable().getValueBond(i,j).toString()), i,j);

            }

        }

    }
}
