package progetto.network.socket.client;

import progetto.network.connectionstate.Room;
import progetto.network.socket.AbstractNetworkCommand;

import java.util.logging.Logger;

/**
 * updates the room info
 */
public class SetRoomInfoCommand extends AbstractNetworkCommand<SocketClient>
{
	private Room room = null;
	private static final Logger LOGGER = Logger.getLogger(SetRoomInfoCommand.class.getName());

	public SetRoomInfoCommand(Room r)
	{
		if (r != null)
			room = r.deepCopy();
	}


	protected void execute(SocketClient mng) {
		if (room == null)
		{
			LOGGER.info("Receiving and empty room");
		}
		else
		{
			LOGGER.info("Receiving a room");
		}
		mng.setRoomInfo(room);
	}
}
