package progetto.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * this is the inteface that is exposed by the server rmi implementation to allow a client to connect.
 */
interface IRemoteLogger extends Remote {
	/**
	 * The player provides his remoteClientSession and receives a new remoteServerSession
	 * @param remote the object that will be used by the server to send enforces
	 * @return the object that will be used by the client to send requests
	 * @throws RemoteException all remote exceptions are thrown
	 */
	IRemoteServerSession login(IRemoteClientSession remote) throws RemoteException;

}
