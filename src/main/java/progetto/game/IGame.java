package progetto.game;

public interface IGame {
	IDataContainer<CommandQueueData> getCommandQueue();
	IDataContainer<RoundTrackData> getRoundTrack();
	IPlayerBoard getPlayerBoard(int index);
	IMainBoard getMainBoard();
}
