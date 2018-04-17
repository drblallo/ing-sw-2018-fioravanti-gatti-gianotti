package progetto.network.rmi;

import progetto.network.AbstractEnforce;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface IRemoteClientSession extends Remote {
	void sayGoodBye() throws RemoteException;

	void sendMessage(String message) throws RemoteException;

	void sendEnforce(AbstractEnforce enforce) throws RemoteException;

}
