package progetto.network.rmi;

import progetto.network.IEnforce;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface IRemoteClientSession extends Remote {
	void sayGoodBye() throws RemoteException;

	void sendMessage(String message) throws RemoteException;

	void sendEnforce(IEnforce enforce) throws RemoteException;

}
