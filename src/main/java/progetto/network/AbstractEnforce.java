package progetto.network;

import java.io.Serializable;

/**
 * This is the class that must be extended to add a functionality
 * it's up to the implemented transport mechanism tu ensure that it can be transported.
 */
public abstract class AbstractEnforce implements Serializable{

	public abstract void execute(ClientConnection c);
}
