package progetto.network;

import java.io.Serializable;

/**
 * This is the class that must be extended to add a functionality
 * it's up to the implemented transport mechanism tu ensure that it can be transported.
 * @author Massimo
 */
public interface IEnforce extends Serializable {

	/**
	 * extend this class to implement a behaviour that is invoked in the client
	 * @param c the client connection that received this enforce
	 */
	void execute(ClientConnection c);
}
