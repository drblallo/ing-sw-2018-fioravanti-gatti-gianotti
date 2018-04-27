package progetto.game;

public interface IExecuibleGame extends IGame
{
	void sendAction(AbstractGameAction action);
	void processAllPendingAction();
	void processAction();
}
