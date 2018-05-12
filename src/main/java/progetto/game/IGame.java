package progetto.game;

public interface IGame {
	AbstractProcessor<AbstractGameAction> getActionQueue();
	RoundTrack getRoundTrack();
	PlayerBoard getPlayerBoard(int index);
	MainBoard getMainBoard();
	DiceBag getDiceBag();
}
