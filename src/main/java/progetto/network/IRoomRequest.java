package progetto.network;

import java.io.Serializable;

/**
 * This is the class that must be extended to add a functionality
 * it's up to the implemented transport mechanism tu ensure that it can be transported.
 * @author Massimo
 */
public interface IRoomRequest extends Serializable {

	/**
	 * extend this method to add a behaviour that can be invoked by the player
	 *
	 * @param room the room that received this request
	 * @param serverConnection the server connection that spawned this request
	 */
	void execute(AbstractRoom room, ServerConnection serverConnection);
}
