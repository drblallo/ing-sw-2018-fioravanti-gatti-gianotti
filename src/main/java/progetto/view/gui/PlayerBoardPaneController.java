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

/**
 * this is the class that handles the player board fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 */
public class PlayerBoardPaneController extends AbstractController{

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

    /**
     *
     * @param numberOfPlayerBoard number of the player board required
     * @param view the current gui view
     * @return a pane containing the player board required
     */
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

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     * @param view the current gui view
     * @param numberOfPlayerBoard the number of the player board associated to this controller
     */
    public void setup(GUIView view, int numberOfPlayerBoard){
    	super.setUp(view);
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

    /**
     * called when the user clicks on a dice among the placed ones
     * if possible send a ToolCardSetPlacedDiceAction or a ToolCardSetSecondPlacedDiceAction to the controller
     * @param event click on the dice
     * @param y y position of the dice clicked
     * @param x x position of the dice clicked
     */
    private void onMouseClicked(MouseEvent event, int y, int x){
        IModel model = getController().getModel();
        int currentChair = getController().getChair();

        AbstractGameAction toolCardSetPlacedDiceAction;

        if (!model.getRoundInformation().getData().getToolCardParameters().isFirstDiceSet())
            toolCardSetPlacedDiceAction = new ToolCardSetPlacedDiceAction(currentChair, y,x);
        else toolCardSetPlacedDiceAction = new ToolCardSetSecondPlacedDiceAction(currentChair, y, x);

        if (toolCardSetPlacedDiceAction.canBeExecuted(getController().getModel()))
            getController().sendAction(toolCardSetPlacedDiceAction);

        event.consume();
    }

    /**
     * called when the user end drag and drop on a node (which is an imageView)
     * @param event end drag and drop
     * @param y y position of the associated node
     * @param x x position of the associated node
     */
    private void onDragDropped(DragEvent event, int y, int x){
        String recived = event.getDragboard().getString();
        int numberOfDice = Integer.parseInt(recived);
        getController().sendAction(new PlaceDiceAction(getController().getChair(), numberOfDice, y, x));
    }

    /**
     * called when the user is passing over a node (during drag & drop)
     * @param event passing over a node
     * @param y y position of the associated node
     * @param x x position of the associated node
     */
    private void onDragOver(DragEvent event, int y, int x){
        if(event.getDragboard().hasString()){
            String recived = event.getDragboard().getString();
            int numberOfDice = Integer.parseInt(recived);
            if (new PlaceDiceAction(getController().getChair(), numberOfDice, y, x)
                    .canBeExecuted(getController().getModel())){
                event.acceptTransferModes(TransferMode.COPY);
            }
        }
    }

    /**
     * Add or remove chooseWindowFrameButton according to the state of the game
     */
    private void updateChooseWindowFrameButton()
    {
        PlayerBoardData playerBoardData = getModel().getPlayerBoard(numberOfPlayerBoard).getData();
        if(!playerBoardData.getWindowFrameIsSet() && numberOfPlayerBoard == getChair()
                &&getModel().getMainBoard().getData().getGameState().getClass()
                == FrameSelectionState.class)
        {
            if (!topBox.getChildren().contains(chooseWindowFrame))
                topBox.getChildren().add(chooseWindowFrame);
        }
        else topBox.getChildren().remove(chooseWindowFrame);

    }

    /**
     * update the player board associated to this controller
     */
    private void updatePlayerBoard() {

        PlayerView currentPlayer = getController().getCurrentRoom().getPlayerOfChair(numberOfPlayerBoard);

        if (currentPlayer != null)
            nameOfPlayer.setText(currentPlayer.getName());
        else nameOfPlayer.setText("Giocatore: " + numberOfPlayerBoard);
        updateChooseWindowFrameButton();
        updateDicePlacedFrame();

    }

    /**
     * update the dice placed on the player board associated to this controller
     */
    private void updateDicePlacedFrame(){

        DicePlacedFrameData dicePlacedFrameData = getController().getModel().getPlayerBoard(numberOfPlayerBoard)
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

    /**
     * set as image of a cell the image of the corresponding dice in the window frame
     * @param y y position of the cell
     * @param x x position of the cell
     * @param imageView imageView to set
     */
    private void setWindowFrameCell(int y, int x, ImageView imageView){

        WindowFrame windowFrame = getController().getModel().getPlayerBoard(numberOfPlayerBoard)
                .getData().getWindowFrame();
        if (windowFrame.getColorBond(y, x) != null) {
            imageView.setImage(textureDatabase.getDice(windowFrame.getColorBond(y, x), -1));
        } else if (windowFrame.getValueBond(y, x) != null) {
            imageView.setImage(textureDatabase.getDice(null,
                    windowFrame.getValueBond(y, x).ordinal() + 1));
        }
        else imageView.setImage(textureDatabase.getDice(null, -1));
    }

    /**
     * called when chooseWindowFrameButton is clicked
     * show the scene containing the 4 possible window frames for the user
     */
    @FXML
    private void onChooseWindowFrameClicked(){

        IModel model = getController().getModel();

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
                    .getPlayerBoard(numberOfPlayerBoard).getData(), getView());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane));
            stage.setTitle("Scelta vetrata");
            stage.show();
        }
    }
}