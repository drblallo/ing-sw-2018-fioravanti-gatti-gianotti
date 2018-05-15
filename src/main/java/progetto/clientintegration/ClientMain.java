package progetto.clientintegration;


import progetto.game.Game;
import progetto.game.IExecuibleGame;
import progetto.serverintegration.ServerMain;

public final class ClientMain {

    private ClientMain()
    {
        //constructor hiding
    }

    private static ClientGame clientGame;

    public static ClientGame getGame() {
        return clientGame;
    }

    public static void setClientGame(ClientGame newClientGame){

        if(newClientGame.getClientConnection().isRunning()){

            clientGame= newClientGame;

        }

    }

    public static void main(String[] args){

        ServerMain.main(args);
        ClientWindow.launchWindow(args);

    }

}
