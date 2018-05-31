package progetto.integration.client.view;

import javafx.stage.Stage;
import progetto.integration.client.ClientController;
import progetto.integration.client.ClientGame;
import progetto.view.gui.GamePaneController;
import progetto.view.gui.ViewStateMachine;

public class GUIView implements IView
{

	private ClientController controller;
	private final ViewStateMachine viewStateMachine;
	private boolean started = false;
	private final Stage stage;


	public GUIView(Stage primaryStage)
	{
		viewStateMachine = new ViewStateMachine(primaryStage);
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

			startingPaneControllerViewState.show();

			new ClientViewState<SocketRMIChoicePaneController>(this,
					"SocketRMIChoicePane.fxml", SocketRMIChoicePaneController.class);

			new ClientViewState<ExistingGamesPaneController>(this,
					"ExistingGamesPane.fxml", ExistingGamesPaneController.class);

			new ClientViewState<RoomsPaneController>(this,
					"RoomsPane.fxml", RoomsPaneController.class);

			new GameViewState(this, "GamePane.fxml", GamePaneController.class);
		}

		if (visible)
			stage.show();
		else
			stage.hide();
	}

	@Override
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public ClientController getController() {
		return controller;
	}

	public void setCurrentGame(ClientGame game)
	{
		viewStateMachine.setCurrentGame(game);
	}


}
