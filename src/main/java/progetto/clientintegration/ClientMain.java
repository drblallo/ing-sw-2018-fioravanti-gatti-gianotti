package progetto.clientintegration;


import progetto.game.Game;
import progetto.game.IExecuibleGame;
import progetto.serverintegration.ServerMain;

public final class ClientMain {

    private ClientMain()
    {
        //constructor hiding
    }

    public static void main(String[] args){

        ServerMain.main(args);
        ClientWindow.launchWindow(args);

    }

}
