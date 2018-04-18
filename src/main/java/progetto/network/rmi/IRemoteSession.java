package progetto.network.rmi;

import progetto.network.AbstractRoomRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface IRemoteSession extends Remote {
	void sayGoodBye() throws RemoteException;

	void sendRequest(AbstractRoomRequest request) throws RemoteException;
}
