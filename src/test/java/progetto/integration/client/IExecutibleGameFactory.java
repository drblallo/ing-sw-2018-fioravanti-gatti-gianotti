package progetto.integration.client;

import progetto.controller.IGameController;

public abstract class IExecutibleGameFactory {

	public abstract IGameController getGame();
}
