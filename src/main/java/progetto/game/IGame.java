package progetto.game;

public interface IGame {
	DataContainer<CommandQueueData> getCommandQueue();
	DataContainer<RoundTrackData> getRoundTrack();
	AbstractPlayerBoard getPlayerBoard(int index);
	AbstractMainBoard getMainBoard();
}
