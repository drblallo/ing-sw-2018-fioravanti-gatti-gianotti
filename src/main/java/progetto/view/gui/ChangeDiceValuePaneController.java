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

/**
 * this is the class that handles the change dice value fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
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

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     */
    @Override
    public void setUp(GUIView view){
    	super.setUp(view);
        view.getController().getObservable().getRoundInformation().addObserver(ogg -> Platform.runLater(this::update));
        for (int i = 1; i<MAX_VALUE_OF_DICE+1; i++)
            chooseNumberOfDice.getItems().add(i);
    }

    /**
     * called when round information changes
     * add and remove this scene when it is needed
     * update the scene with the selected dice
     */
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

    /**
     * called when ChooseButton is clicked
     * send to the controller a ToolCardSetDiceValueAction with the selected value
     */
    @FXML
    private void onChooseButtonClicked(){
        int newValue = chooseNumberOfDice.getSelectionModel().getSelectedItem();
        getController().sendAction(new ToolCardSetDiceValueAction(getChair(),  newValue));
    }

}
