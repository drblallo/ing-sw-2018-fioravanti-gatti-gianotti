package progetto.network.rmi;

import progetto.network.AbstractRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface IRemoteSession extends Remote {
	void sayGoodBye() throws RemoteException;

	void sendRequest(AbstractRequest request) throws RemoteException;
}
