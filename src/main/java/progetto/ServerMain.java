package progetto;

import progetto.network.NetworkServer;
import progetto.network.localconnection.LocalConnectionModule;
import progetto.network.rmi.RMIModule;
import progetto.network.socket.SocketServer;

public class ServerMain {



    public static void main(String[] args){


        NetworkServer networkServer = new NetworkServer(new ServerGameFactory());

        networkServer.addModules(new RMIModule(Settings.getSettings().getRmiPort()));
        networkServer.addModules(new SocketServer(Settings.getSettings().getSocketPort()));
        networkServer.addModules(new LocalConnectionModule());
        networkServer.start();

 }

}
