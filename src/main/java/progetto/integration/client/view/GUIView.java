package progetto.integration.client.view;

import javafx.stage.Stage;
import progetto.integration.client.ClientController;
import progetto.view.commandline.ICommandProcessor;
import progetto.view.gui.GamePaneController;
import progetto.view.gui.ViewStateMachine;

public class GUIView extends AbstractView
{

	private final ClientCommandProcessor commandProcessor;
	private final ViewStateMachine viewStateMachine;
	private boolean started = false;
	private final Stage stage;


	public GUIView(Stage primaryStage, ClientController controller)
	{
		super(controller);
		viewStateMachine = new ViewStateMachine(primaryStage);
		stage = primaryStage;
		commandProcessor = new ClientCommandProcessor(controller);
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

	public void onGameChanged()
	{
		viewStateMachine.setCurrentGame(getController());
		commandProcessor.reaload();
	}

	public ICommandProcessor getCommandProcessor()
	{
		return commandProcessor;
	}


}
