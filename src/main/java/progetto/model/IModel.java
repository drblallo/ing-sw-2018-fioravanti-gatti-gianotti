package progetto.model;

public interface IModel {
	Container<CommandQueueData> getCommandQueue();
	Container<RoundTrackData> getRoundTrack();
	AbstractPlayerBoard getPlayerBoard(int index);
	AbstractMainBoard getMainBoard();
}
