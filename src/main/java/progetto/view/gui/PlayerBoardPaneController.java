package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import progetto.controller.PlaceDiceAction;
import progetto.controller.ToolCardSetPlacedDiceAction;
import progetto.controller.ToolCardSetSecondPlacedDiceAction;
import progetto.model.*;
import progetto.network.PlayerView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerBoardPaneController {

    private TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
    @FXML
    private TilePane tilePane;
    @FXML
    private PickedDicesSlotPaneController pickedDicesSlotPaneController;
    @FXML
    private Label nameOfPlayer;
    @FXML
    private HBox topBox;
    @FXML
    private Button chooseWindowFrame;
    private int numberOfPlayerBoard;
    private static final Logger LOGGER = Logger.getLogger(PlayerBoardPaneController.class.getName());
    private GUIView view;

    public static Pane getPlayerBoard(int numberOfPlayerBoard, GUIView view){
        Pane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(GamePaneController.class.getResource("PlayerBoardPane.fxml"));
        try{
            pane = fxmlLoader.load();
        }catch (IOException e){
            pane = null;
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        PlayerBoardPaneController playerBoardPaneControllers = fxmlLoader.getController();
        playerBoardPaneControllers.setup(view, numberOfPlayerBoard);
        return pane;
    }

    public void setup(GUIView view, int numberOfPlayerBoard){
        this.view = view;
        this.numberOfPlayerBoard = numberOfPlayerBoard;
        topBox.getChildren().remove(chooseWindowFrame);
        view.getController().getObservable().getPlayerBoard(numberOfPlayerBoard)
                .addObserver(ogg-> Platform.runLater(this::updatePlayerBoard));
        view.getController().getObservable().getPlayerBoard(numberOfPlayerBoard).getDicePlacedFrame()
            .addObserver(ogg -> Platform.runLater(this::updateDicePlacedFrame));
        pickedDicesSlotPaneController.setup(view, numberOfPlayerBoard);

        ImageView imageView;

        for (int y = 0; y < DicePlacedFrameData.MAX_NUMBER_OF_ROWS; y++) {
            for (int x = 0; x < DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; x++) {
                imageView = (ImageView) tilePane.getChildren()
                        .get(y*DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS +x);
                final int finalY = y;
                final int finalX = x;
                imageView.setOnDragOver(event -> onDragOver(event, finalY, finalX));
                imageView.setOnDragDropped(event -> onDragDropped(event, finalY, finalX));
                imageView.setOnMouseClicked(event -> onMouseClicked(event, finalY, finalX));
                setWindowFrameCell(y,x, imageView);
            }
        }

        updatePlayerBoard();
        updateDicePlacedFrame();

    }

    private void onMouseClicked(MouseEvent event, int y, int x){
        IModel model = view.getController().getModel();
        int currentChair = view.getController().getChair();

        AbstractGameAction toolCardSetPlacedDiceAction;

        if (!model.getRoundInformation().getData().getToolCardParameters().isFirstDiceSet())
            toolCardSetPlacedDiceAction = new ToolCardSetPlacedDiceAction(currentChair, y,x);
        else toolCardSetPlacedDiceAction = new ToolCardSetSecondPlacedDiceAction(currentChair, y, x);

        if (toolCardSetPlacedDiceAction.canBeExecuted(view.getController().getModel()))
            view.getController().sendAction(toolCardSetPlacedDiceAction);

        event.consume();
    }

    private void onDragDropped(DragEvent event, int y, int x){
        String recived = event.getDragboard().getString();
        int numberOfDice = Integer.parseInt(recived);
        view.getController().sendAction(new PlaceDiceAction(view.getController().getChair(),
                numberOfDice, y, x));
    }

    private void onDragOver(DragEvent event, int y, int x){
        if(event.getDragboard().hasString()){
            String recived = event.getDragboard().getString();
            int numberOfDice = Integer.parseInt(recived);
            if (new PlaceDiceAction(view.getController().getChair(), numberOfDice, y, x)
                    .canBeExecuted(view.getController().getModel())){
                event.acceptTransferModes(TransferMode.COPY);
            }
        }
    }

    private void updateChooseWindowFrameButton(PlayerBoardData playerBoardData)
    {
        if(!playerBoardData.getWindowFrameIsSet() && numberOfPlayerBoard == view.getController().getChair()
                &&view.getController().getModel().getMainBoard().getData().getGameState().getClass()
                == FrameSelectionState.class)
        {
            if (!topBox.getChildren().contains(chooseWindowFrame))
                topBox.getChildren().add(chooseWindowFrame);
        }
        else topBox.getChildren().remove(chooseWindowFrame);

    }

    private void updatePlayerBoard() {

        IModel model = view.getController().getModel();
        PlayerBoardData playerBoardData = model.getPlayerBoard(numberOfPlayerBoard).getData();
        PlayerView currentPlayer = view.getController().getCurrentRoom().getPlayerOfChair(numberOfPlayerBoard);

        if (currentPlayer != null)
            nameOfPlayer.setText(currentPlayer.getName());
        else nameOfPlayer.setText("Giocatore: " + numberOfPlayerBoard);
        updateChooseWindowFrameButton(playerBoardData);
        updateDicePlacedFrame();

    }

    private void updateDicePlacedFrame(){

        DicePlacedFrameData dicePlacedFrameData = view.getController().getModel().getPlayerBoard(numberOfPlayerBoard)
                .getDicePlacedFrame().getData();
        ImageView imageView;
        for (int y = 0; y < DicePlacedFrameData.MAX_NUMBER_OF_ROWS; y++) {
            for (int x = 0; x < DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; x++) {
                imageView = (ImageView) tilePane.getChildren()
                        .get(y*DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS +x);
                    if(dicePlacedFrameData.getDice(y,x)!=null){
                        imageView.setImage(textureDatabase.getDice
                                (dicePlacedFrameData.getDice(y,x).getGameColor(),
                                        dicePlacedFrameData.getDice(y,x).getValue().ordinal()+1 ));}
                    else setWindowFrameCell(y,x,imageView);
                }
            }
        }

    private void setWindowFrameCell(int y, int x, ImageView imageView){

        WindowFrame windowFrame = view.getController().getModel().getPlayerBoard(numberOfPlayerBoard)
                .getData().getWindowFrame();
        if (windowFrame.getColorBond(y, x) != null) {
            imageView.setImage(textureDatabase.getDice(windowFrame.getColorBond(y, x), -1));
        } else if (windowFrame.getValueBond(y, x) != null) {
            imageView.setImage(textureDatabase.getDice(null,
                    windowFrame.getValueBond(y, x).ordinal() + 1));
        }
        else imageView.setImage(textureDatabase.getDice(null, -1));
    }

    @FXML
    private void onChooseWindowFrameClicked(){

        IModel model = view.getController().getModel();

        if(model.getMainBoard().getData().getGameState().getClass()==FrameSelectionState.class) {
            FXMLLoader fxmlLoader = new FXMLLoader(PlayerBoardPaneController
                    .class.getResource("ChooseWindowFramePane.fxml"));
            Pane pane;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                pane = null;
            }
            ChooseWindowFramePaneController chooseWindowFramePaneController = fxmlLoader.getController();
            chooseWindowFramePaneController.setup(model
                    .getPlayerBoard(numberOfPlayerBoard).getData(), view.getController(), view);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane));
            stage.setTitle("Scelta vetrata");
            stage.show();
        }
    }
}