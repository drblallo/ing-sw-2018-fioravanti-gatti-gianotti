package progetto.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

interface IRemoteLogger extends Remote {
	IRemoteSession login(IRemoteClientSession remote) throws RemoteException;
}
