package progetto.network.rmi;

import progetto.network.IRoomRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the object that is created on the server and is sent to the client to allow him to
 * send requests
 */
interface IRemoteServerSession extends Remote {
	/**
	 * notifies the server that the connection is getting closed
	 * @throws RemoteException all remote exceptions are thrown
	 */
	void sayGoodBye() throws RemoteException;

	/**
	 * sends a request to the server
	 * @param request the request to be sent
	 * @throws RemoteException all remote exceptions are thrown
	 */
	void sendRequest(IRoomRequest request) throws RemoteException;

	/**
	 * notifies that a new a ping has been received from the client
	 * @throws RemoteException all remote exceptions are thrown
	 */
	void ping() throws RemoteException;
}
