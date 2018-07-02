package progetto.view.gui;

import javafx.stage.Stage;
import progetto.AbstractView;
import progetto.ClientController;

public class GUIView extends AbstractView
{

	private final ViewStateMachine viewStateMachine;
	private boolean started = false;
	private final Stage stage;


	public GUIView(Stage primaryStage, ClientController controller)
	{
		super(controller);
		viewStateMachine = new ViewStateMachine(primaryStage, this);
		stage = primaryStage;
	}

	public ViewStateMachine getViewStateMachine() {
		return viewStateMachine;
	}

	@Override
	public void setVisible(boolean visible) {
		if (!started)
		{
			started = true;
			ClientViewState<StartingPaneController> startingPaneControllerViewState =
					new ClientViewState<>(this, "StartingPane.fxml", StartingPaneController.class);
			startingPaneControllerViewState.show(false);
			new ClientViewState<SocketRMIChoicePaneController>(this,
					"SocketRMIChoicePane.fxml", SocketRMIChoicePaneController.class);
			new ClientViewState<ExistingGamesPaneController>(this,
					"ExistingGamesPane.fxml", ExistingGamesPaneController.class);
			new ClientViewState<RoomsPaneController>(this,
					"RoomsPane.fxml", RoomsPaneController.class);
			new ClientViewState<OtherPlayersPaneController>(this,
					"OtherPlayersPane.fxml", OtherPlayersPaneController.class);
			new ClientViewState<ChatPaneController>(this, "ChatPane.fxml", ChatPaneController.class);
			new ClientViewState<GamePaneController>(this, "GamePane.fxml", GamePaneController.class);
			new ClientViewState<EndGamePaneController>(this, "EndGamePane.fxml", EndGamePaneController.class);
		}

		if (visible)
			stage.show();
		else
			stage.hide();
	}

	public void onGameChanged()
	{
		if (getController().thereIsGame())
			viewStateMachine.setCurrentGame(getController());
		else viewStateMachine.getStateFromName("StartingPane.fxml").show(false);
	}

}
