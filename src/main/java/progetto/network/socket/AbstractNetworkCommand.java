package progetto.network.socket;


import java.io.Serializable;

/**
 * This class is the class that must be extended to allow the client and server to exchange informations
 * @param <T>
 */
abstract class AbstractNetworkCommand<T extends AbstractSocketManager> implements Serializable
{
	abstract void execute(T mng);
}
