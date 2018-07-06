package progetto.model;

/**
 * @author Michele
 */
public interface ObservableModel extends IModel
{
	Container<CommandQueueData> getCommandQueue();
	Container<RoundTrackData> getRoundTrack();
	Container<RoundInformationData> getRoundInformation();
	AbstractPlayerBoard getPlayerBoard(int index);
	AbstractMainBoard getMainBoard();
}
