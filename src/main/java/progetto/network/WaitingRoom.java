package progetto.network;

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
	RoomView getView()
	{
		return new RoomView(name, getRoomID());
	}

	void processCommand(String syncString, int callerID)
	{
		//waiting room, we drop inputs
	}

	WaitingRoom()
	{
		super("Waiting Room", -1, null);
	}
}
