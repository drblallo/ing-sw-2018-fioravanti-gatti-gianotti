package progetto.network;

final class SetPlayerInfoEnforce extends AbstractEnforce{

	private int playerID;

	public void execute(ClientConnection c) {
		c.setPlayerID(playerID);
	}

	SetPlayerInfoEnforce(int id)
	{
		playerID = id;
	}

}
