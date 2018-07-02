package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import progetto.controller.*;
import progetto.model.*;

import java.util.List;

public class UseToolCardPaneController extends AbstractController{

    @FXML
    private HBox chooseDiceFromRoundTrack;
    @FXML
    private HBox setDiceValue;
    @FXML
    private ChoiceBox<Integer> valueOfDice;
    @FXML
    private HBox increaseDiceValue;
    @FXML
    private ChoiceBox<Integer> increasedDiceValue;
    @FXML
    private HBox chooseDiceFromPicked;
    @FXML
    private HBox chooseDiceFromPlaced;
    @FXML
    private HBox chooseSecondDiceFromPlaced;
    @FXML
    private ImageView roundTrackDice;
    @FXML
    private ImageView extractedDicesDice;
    @FXML
    private ImageView firstPlacedDice;
    @FXML
    private ImageView secondPlacedDice;
    @FXML
    private Label tokens;
    @FXML
    private VBox toDoVBox;
    @FXML
    private ImageView cardImage;
    @FXML
    private Label roundDiceLabel;
    @FXML
    private Label pickedDiceLabel;
    @FXML
    private Label placedDiceLabel;
    @FXML
    private Label secondPlacedDiceLabel;
    @FXML
    private Label singlePlayerLabel;
    @FXML
    private ImageView singlePlayerDice;
    @FXML
    private HBox chooseSinglePlayerDice;
    @FXML
    private HBox tokensHBox;
    @FXML
    private VBox topVBox;
    private TextureDatabase textureDatabase;
    private static final int DICE_DIMENSION = 55;


    @Override
    public void setUp(GUIView view) {
    	super.setUp(view);
        textureDatabase = TextureDatabase.getTextureDatabase();
        view.getController().getObservable().getRoundInformation().addObserver(ogg -> Platform.runLater(this::update));

        toDoVBox.getChildren().clear();
        topVBox.getChildren().removeAll(tokensHBox, chooseSinglePlayerDice);

        for (int i = 1; i < ToolCardSetDiceValueAction.MAX_VALUE + 1; i++)
            valueOfDice.getItems().add(i);

        valueOfDice.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> view.getController().sendAction(
                        new ToolCardSetDiceValueAction(view.getController().getChair(), newValue)));

        increasedDiceValue.getItems().addAll(-1, 1);

        increasedDiceValue.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                {
                    int increaseDecrease;

                    if (newValue == 1)
                        increaseDecrease = 0;
                    else increaseDecrease = 1;

                    view.getController().sendAction(new ToolCardSetIncreaseDecreaseAction
                            (view.getController().getChair(), increaseDecrease));
                });
        textureDatabase = TextureDatabase.getTextureDatabase();

    }

    private void update() {

        IModel model = getModel();
        if (model.getMainBoard().getData().getGameState().getClass() != ToolCardState.class ||
                model.getRoundInformation().getData().getCurrentPlayer() != getController().getChair())
            return;

        ToolCardParameters toolCardParameters = model.getRoundInformation().getData().getToolCardParameters();
        ToolCard toolCard = model.getMainBoard().getData().getToolCards()
                .get(toolCardParameters.getNCard());
        MainBoardData mainBoardData = model.getMainBoard().getData();

        if (cardImage.getImage() != textureDatabase.getToolCard(toolCard.getIndex()))
            cardImage.setImage(textureDatabase.getToolCard(toolCard.getIndex()));

        int toolCardPosition = toolCardParameters.getNCard();
        tokens.setText(Math.min(1, Math.max(0, mainBoardData.getNCallToolCard(toolCardPosition))) + 1 + "");

        List<Class> toolCardActionList = ToolCardActionList.getInstance().getList(toolCard.getIndex());
        addRequestedActions(toolCardActionList, model);
    }

    private void addRequestedActions(List<Class> toolCardActionList, IModel model){
        toDoVBox.getChildren().clear();
        topVBox.getChildren().removeAll(tokensHBox, chooseSinglePlayerDice);
        clearDices();

        ToolCardParameters toolCardParameters = model.getRoundInformation().getData().getToolCardParameters();
        if (model.getMainBoard().getData().getPlayerCount() == 1)
            setSinglePlayerDiceAction(toolCardParameters, model);
        else topVBox.getChildren().add(tokensHBox);
        for (Class c : toolCardActionList) {

            if (c == ToolCardSetDiceRoundTrackAction.class) {
                toolCardSetDiceRoundTrackAction(toolCardParameters, model);
            }
            if (c == ToolCardSetDiceValueAction.class) {
                toDoVBox.getChildren().add(setDiceValue);
            }
            if (c == ToolCardSetIncreaseDecreaseAction.class) {
                toDoVBox.getChildren().add(increasedDiceValue);
            }
            if (c == ToolCardSetPickedDiceAction.class) {
                toolCardSetPickedDiceAction(toolCardParameters, model);
            }
            if (c == ToolCardSetPlacedDiceAction.class) {
                toolCardSetPlacedDiceAction(toolCardParameters, model);
            }
            if (c == ToolCardSetSecondPlacedDiceAction.class){
                toolCardSetSecondDiceAction(toolCardParameters, model);
            }
        }
    }

    private void setSinglePlayerDiceAction(ToolCardParameters toolCardParameters, IModel model){
        topVBox.getChildren().add(chooseSinglePlayerDice);
        if (toolCardParameters.getSPDice()>=0){
            Dice dice = model.getMainBoard().getExtractedDices().getData().getDice(toolCardParameters.getSPDice());
            loadDice(singlePlayerDice, dice);
            singlePlayerLabel.setText("Hai scelto dai dadi piazzati: ");
        }
        else singlePlayerLabel.setText("Secgli il dado da sacrificare: ");
    }

    private void toolCardSetSecondDiceAction(ToolCardParameters toolCardParameters, IModel model){
        toDoVBox.getChildren().add(chooseSecondDiceFromPlaced);
        if (toolCardParameters.isSecondDiceSet()){
            Dice dice = model.getPlayerBoard(getController().getChair()).getDicePlacedFrame()
                    .getData().getDice(toolCardParameters.getYPlacedDice2(), toolCardParameters.getXPlacedDice2());
            loadDice(secondPlacedDice, dice);
            secondPlacedDiceLabel.setText("Hai scelto dai dadi piazzati:");
        }
        else secondPlacedDiceLabel.setText("Scegli un altro dado tra quelli piazzati");
    }

    private void toolCardSetPlacedDiceAction(ToolCardParameters toolCardParameters, IModel model) {
        toDoVBox.getChildren().add(chooseDiceFromPlaced);
        if (toolCardParameters.isFirstDiceSet()) {
            Dice dice = model.getPlayerBoard(getController().getChair()).getDicePlacedFrame()
                    .getData().getDice(toolCardParameters.getYPlacedDice(), toolCardParameters.getXPlacedDice());
            loadDice(firstPlacedDice, dice);
            placedDiceLabel.setText("Hai scelto dai dadi piazzati: ");
        }
        else placedDiceLabel.setText("Scegli un dado tra quelli piazzati");
    }

    private void toolCardSetPickedDiceAction(ToolCardParameters toolCardParameters, IModel model) {
        toDoVBox.getChildren().add(chooseDiceFromPicked);
        if (toolCardParameters.getNDice() >= 0) {
            Dice dice = model.getPlayerBoard(getController().getChair()).getPickedDicesSlot()
                    .getData().getDicePlacementCondition(toolCardParameters.getNDice()).getDice();
            loadDice(extractedDicesDice, dice);
            pickedDiceLabel.setText("Hai scelto dalla riserva: ");
        } else pickedDiceLabel.setText("Scegli un dado tra quelli della riserva");
    }

    private void toolCardSetDiceRoundTrackAction(ToolCardParameters toolCardParameters, IModel model) {
        toDoVBox.getChildren().add(chooseDiceFromRoundTrack);
        if (toolCardParameters.getNDiceRT()>= 0) {
            Dice dice = model.getRoundTrack().getData().getDice
                    (toolCardParameters.getRound(), toolCardParameters.getNDiceRT());
            loadDice(roundTrackDice, dice);
            roundDiceLabel.setText("Hai scelto dal TR: ");
        } else roundDiceLabel.setText("Scegli un dado dal tracciato dei round");
    }

    private void loadDice(ImageView imageView, Dice dice){
        if (imageView.getImage()!=textureDatabase.getDice(dice.getGameColor(), dice.getValue().ordinal()+1)){
            imageView.setFitHeight(DICE_DIMENSION);
            imageView.setFitWidth(DICE_DIMENSION);
            imageView.setImage(textureDatabase.getDice(dice.getGameColor(), dice.getValue().ordinal()+1));
        }
    }

    private void clearDice(ImageView imageView){
        imageView.setImage(null);
        imageView.setFitWidth(0);
        imageView.setFitHeight(0);
    }

    private void clearDices(){
        clearDice(roundTrackDice);
        clearDice(extractedDicesDice);
        clearDice(firstPlacedDice);
        clearDice(secondPlacedDice);
        clearDice(singlePlayerDice);
    }

    @FXML
    private void onCancelButtonClicked() {
        IModel model = getModel();
        if (model.getMainBoard().getData().getGameState().getClass() != ToolCardState.class ||
                model.getRoundInformation().getData().getCurrentPlayer() != getController().getChair())
            return;
        clearDices();
        getController().sendAction(new CancelToolCardUseAction(getController().getChair()));
    }

    @FXML
    private void onConfirmButtonClicked() {
        IModel model = getController().getModel();
        if (model.getMainBoard().getData().getGameState().getClass() != ToolCardState.class ||
                model.getRoundInformation().getData().getCurrentPlayer() != getController().getChair())
            return;
        ExecuteToolCardAction executeToolCard1Action = new ExecuteToolCardAction(getController().getChair());
        if (executeToolCard1Action.canBeExecuted(model)) {
            clearDices();
            getController().sendAction(executeToolCard1Action);
        }
    }
}

