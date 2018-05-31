package progetto.integration.client.view;


import progetto.integration.client.ClientController;
import progetto.integration.client.ClientGame;

public interface IView {

	void setVisible(boolean visible);
	void setController(ClientController controller);
	ClientController getController();
	void setCurrentGame(ClientGame game);
}
