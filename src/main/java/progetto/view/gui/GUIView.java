package progetto.view.gui;

import javafx.application.Platform;
import javafx.stage.Stage;
import progetto.AbstractView;
import progetto.ClientController;

public class GUIView extends AbstractView
{

	private final StateManager stateManager;
	private boolean started = false;
	private final Stage stage;


	public GUIView(Stage primaryStage, ClientController controller)
	{
		super(controller);
		stateManager = new StateManager(primaryStage, this);
		stage = primaryStage;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	@Override
	public void setVisible(boolean visible) {
		if (!started)
		{
			started = true;
			State<StartingPaneController> startingPaneControllerViewState =
					new State<>(stateManager, "StartingPane.fxml", StartingPaneController.class);
			startingPaneControllerViewState.show(false);
			new State<SocketRMIChoicePaneController>(stateManager,
					"SocketRMIChoicePane.fxml", SocketRMIChoicePaneController.class);
			new State<ExistingGamesPaneController>(stateManager,
					"ExistingGamesPane.fxml", ExistingGamesPaneController.class);
			new State<RoomsPaneController>(stateManager, "RoomsPane.fxml", RoomsPaneController.class);
			new State<OtherPlayersPaneController>(stateManager,
					"OtherPlayersPane.fxml", OtherPlayersPaneController.class);
			new State<ChatPaneController>(stateManager, "ChatPane.fxml", ChatPaneController.class);
			new State<GamePaneController>(stateManager, "GamePane.fxml", GamePaneController.class);
			new State<EndGamePaneController>(stateManager, "EndGamePane.fxml", EndGamePaneController.class);
		}

		if (visible)
			stage.show();
		else
			stage.hide();
	}

	public void onGameChanged()
	{
		if (!getController().thereIsGame())
		{
			Platform.runLater(
					()-> stateManager.getStateFromName("StartingPane.fxml").show(false)
			);
		}
	}

}
