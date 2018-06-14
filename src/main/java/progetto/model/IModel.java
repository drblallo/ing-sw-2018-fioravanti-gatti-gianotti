package progetto.model;

public interface IModel {
	Container<CommandQueueData> getCommandQueue();
	Container<RoundTrackData> getRoundTrack();
	Container<RoundInformationData> getRoundInformation();
	AbstractPlayerBoard getPlayerBoard(int index);
	AbstractMainBoard getMainBoard();
}
