package progetto.network.rmi;

import progetto.network.IEnforce;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * this is the interface of the object that is created on the client and sent to the server as a remote.
 * @author Massimo
 */
interface IRemoteClientSession extends Remote {

	/**
	 * notifies the client that the connection is closing
	 * @throws RemoteException all remote exception are thrown
	 */
	void sayGoodBye() throws RemoteException;

	/**
	 * send a message to the client
	 * @param message the message that must be sent
	 * @throws RemoteException all remote exception are thrown
	 */
	void sendMessage(String message) throws RemoteException;

	/**
	 * send a enforce to the client
	 * @param enforce the enforce that must be sent
	 * @throws RemoteException all remote exception are thrown
	 */
	void sendEnforce(IEnforce enforce) throws RemoteException;

	/**
	 * send a keep alive ping to the client
	 * @throws RemoteException all remote exception are throw
	 */
	void ping() throws RemoteException;
}
