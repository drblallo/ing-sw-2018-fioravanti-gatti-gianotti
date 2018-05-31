package progetto.integration.client;

import progetto.integration.client.view.ClientCommandProcessor;
import progetto.integration.client.view.IView;
import progetto.view.commandline.ICommandProcessor;

import java.util.ArrayList;
import java.util.List;

public class ClientController
{

    public ClientController()
    {
        commandProcessor = new ClientCommandProcessor(this);
    }

    private final ClientCommandProcessor commandProcessor;
    private ClientGame clientGame;
    private final ExistingGames existingGames = new ExistingGames();
    private List<IView>  views = new ArrayList<>();

    public void setCurrentClientGame(ClientGame clientGame){

        this.clientGame = clientGame;
        commandProcessor.reaload();

        for (IView v : views)
        {
            v.setCurrentGame(clientGame);
        }
    }

    public ClientGame getCurrentClientGame() {

        return clientGame;

    }

    public ICommandProcessor getClientCommandProcessor() {

        return commandProcessor;

    }

    public ExistingGames getExistingGames()
    {
        return existingGames;
    }

    public void addView(IView view)
    {
        views.add(view);
        view.setController(this);
    }
}
