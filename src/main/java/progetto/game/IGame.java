package progetto.game;

public interface IGame {
	AbstractProcessor<AbstractGameAction> getCommandQueue();
	RoundTrack getRoundTrack();
	PlayerBoard getPlayerBoard(int index);
	MainBoard getMainBoard();
	DiceBag getDiceBag();
	AbstractGameState getGameState();
	int getPlayerCount();
}
