package progetto.serverintegration;

import progetto.clientintegration.SocketRMIChoicePaneController;
import progetto.network.NetworkServer;
import progetto.network.localconnection.LocalConnectionModule;
import progetto.network.rmi.RMIModule;
import progetto.network.socket.SocketServer;

public class ServerMain {

    public final static int DEFAULT_PORT = 8527;

    public static void main(String[] args){

        NetworkServer networkServer = new NetworkServer(new ServerGameFactory());

        networkServer.addModules(new RMIModule());
        networkServer.addModules(new SocketServer(DEFAULT_PORT));
        networkServer.addModules(new LocalConnectionModule());
        networkServer.start();

    }

}