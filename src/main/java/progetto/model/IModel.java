package progetto.model;

public interface IModel {
	DataContainer<CommandQueueData> getCommandQueue();
	DataContainer<RoundTrackData> getRoundTrack();
	AbstractPlayerBoard getPlayerBoard(int index);
	AbstractMainBoard getMainBoard();
}
