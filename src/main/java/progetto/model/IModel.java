package progetto.model;

public interface IModel {
	IContainer<CommandQueueData> getCommandQueue();
	IContainer<RoundTrackData> getRoundTrack();
	IContainer<RoundInformationData> getRoundInformation();
	IPlayerBoard getPlayerBoard(int index);
	IMainBoard getMainBoard();
}
