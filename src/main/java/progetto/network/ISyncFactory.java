package progetto.network;


/**
 * Class provided to the server to be able to spawn a synch object every time it's needed.
 */
public interface ISyncFactory {

	ISync create();
}
