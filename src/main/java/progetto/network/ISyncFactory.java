package progetto.network;


/**
 * Class provided to the server to be able to spawn a synch object every time it's needed.
 * @author Massimo
 */
public interface ISyncFactory {

	/**
	 *
	 * @return a new instance of a sync object
	 */
	ISync create();
}
