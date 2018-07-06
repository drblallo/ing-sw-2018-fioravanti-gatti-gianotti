package progetto.network;

import java.io.Serializable;

/**
 * An implementation of abstract room that is used to hold all the player that have joined the server but have not
 * joined a room yet.
 *
 * All inputs from player are dropped, except server requests. Those requests are sent to the upper level and processed
 * there.
 * @author Massimo
 */
final class WaitingRoom extends AbstractRoom
{

	/**
	 * drops all the input
	 * @param playerID ignored
	 * @param ready ignored
	 */
	void setPlayerReady(int playerID, boolean ready)
	{
		//waiting room, we drop inputs
	}

	/**
	 * drops all the input
	 * @param playerID ignored
	 * @param newChair ignored
	 */
	void setPlayerChair(int playerID, int newChair)
	{
		//waiting room, we drop inputs
	}

	/**
	 * the waiting room can never be deleated
	 * @return false
	 */
	@Override
	boolean canBeDeleted() {
		return false;
	}

	/**
	 *
	 * @return the view of this object
	 */
	@Override
	RoomView getView()
	{
		return new RoomView(name, getRoomID());
	}

	/**
	 * drops the input
	 * @param syncString the string that must be syncd
	 * @param callerID the id of the player that send the string
	 */
	void processCommand(Serializable syncString, int callerID)
	{
		//waiting room, we drop inputs
	}

	/**
	 * creates the waiting room
	 */
	WaitingRoom()
	{
		super("Waiting Room", -1, null);
	}
}
