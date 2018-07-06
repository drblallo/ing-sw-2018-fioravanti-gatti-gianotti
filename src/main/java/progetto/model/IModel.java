package progetto.model;

/**
 * @author Michele
 */
public interface IModel {
	IContainer<CommandQueueData> getCommandQueue();
	IContainer<RoundTrackData> getRoundTrack();
	IContainer<RoundInformationData> getRoundInformation();
	IPlayerBoard getPlayerBoard(int index);
	IMainBoard getMainBoard();
}
