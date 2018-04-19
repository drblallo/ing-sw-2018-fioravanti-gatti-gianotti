package progetto.network.socket;


import java.io.Serializable;

/**
 * This class is the class that must be extended to allow the client and server to exchange informations
 *
 * @param <T>
 */
interface INetworkCommand<T extends AbstractSocket> extends Serializable {
	void execute(T mng);
}
