package progetto.network;

import java.io.Serializable;

/**
 * An implementation of abstract room that is used to hold all the player that have joined the server but have not
 * joined a room yet.
 *
 * All inputs from player are dropped, except server requests. Those requests are sent to the upper level and processed
 * there.
 */
final class WaitingRoom extends AbstractRoom
{

	void setPlayerReady(int playerID, boolean ready)
	{
		//waiting room, we drop inputs
	}

	void setPlayerChair(int playerID, int newChair)
	{
		//waiting room, we drop inputs
	}

	@Override
	boolean canBeDeleted() {
		return false;
	}

	@Override
	RoomView getView()
	{
		return new RoomView(name, getRoomID());
	}

	void processCommand(Serializable syncString, int callerID)
	{
		//waiting room, we drop inputs
	}

	WaitingRoom()
	{
		super("Waiting Room", -1, null);
	}
}
