package progetto.model;

/**
 * Contains all that is necessary to know the state of the game
 * @author Michele
 */
public interface IModel {
	/**
	 *
	 * @return the command queue
	 */
	IContainer<CommandQueueData> getCommandQueue();

	/**
	 *
	 * @return the round track
	 */
	IContainer<RoundTrackData> getRoundTrack();

	/**
	 *
	 * @return the round information
	 */
	IContainer<RoundInformationData> getRoundInformation();

	/**
	 *
	 * @param index index of the required playerboard
	 * @return the playerboard required
	 */
	IPlayerBoard getPlayerBoard(int index);

	/**
	 *
	 * @return the main board
	 */
	IMainBoard getMainBoard();
}
