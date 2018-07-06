package progetto.model;

/**
 * Contains all that is necessary to know the state of the game, the containers returned by this object
 * must be permanent
 * @author Michele
 */
public interface ObservableModel extends IModel
{
	/**
	 *
	 * @return return the command queue
	 */
	Container<CommandQueueData> getCommandQueue();

	/**
	 *
	 * @return the round track
	 */
	Container<RoundTrackData> getRoundTrack();

	/**
	 *
	 * @return the round information
	 */
	Container<RoundInformationData> getRoundInformation();

	/**
	 *
	 * @param index index of the required playerboard
	 * @return the required player board
	 */
	AbstractPlayerBoard getPlayerBoard(int index);

	/**
	 *
	 * @return the main board
	 */
	AbstractMainBoard getMainBoard();
}
