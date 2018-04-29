package progetto.clientintegration;


import progetto.game.Game;
import progetto.game.IExecuibleGame;

public final class ClientMain {

    private ClientMain()
    {
        //constructor hiding
    }

    private static IExecuibleGame game = new Game();

    public static IExecuibleGame getGame() {
        return game;
    }

    public static void main(String[] args){

        ClientWindow.launchWindow(args);

    }

}
