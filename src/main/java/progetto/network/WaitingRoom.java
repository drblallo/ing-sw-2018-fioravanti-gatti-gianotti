package progetto.network;

public final class WaitingRoom extends AbstractRoom
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
