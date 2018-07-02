package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progetto.controller.ToolCardSetDiceValueAction;
import progetto.model.Dice;
import progetto.model.ToolCardParameters;


public class ChangeDiceValuePaneController extends AbstractController{

    private static final int MAX_VALUE_OF_DICE = 6;
    @FXML
    private ImageView diceToChange;
    @FXML
    private ChoiceBox<Integer> chooseNumberOfDice;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox myPane;

    public void setUp(GUIView view){
    	super.setUp(view);
        view.getController().getObservable().getRoundInformation().addObserver(ogg -> Platform.runLater(this::update));
        for (int i = 1; i<MAX_VALUE_OF_DICE+1; i++)
            chooseNumberOfDice.getItems().add(i);
    }

    private void update(){
        ToolCardParameters toolCardParameters = getModel().getRoundInformation()
                .getData().getToolCardParameters();
        if (toolCardParameters.getDice() != null){
            if (!anchorPane.getChildren().contains(myPane))
                anchorPane.getChildren().add(myPane);
        }
        else{
            if (anchorPane.getChildren().contains(myPane))
                anchorPane.getChildren().remove(myPane);
            return;
        }

        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        Dice dice = toolCardParameters.getDice();

        diceToChange.setImage(textureDatabase.getDice(dice.getGameColor(), dice.getValue().ordinal()+1));
    }

    @FXML
    private void onChooseButtonClicked(){
        int newValue = chooseNumberOfDice.getSelectionModel().getSelectedItem();
        getController().sendAction(new ToolCardSetDiceValueAction(getChair(),  newValue));
    }

}
