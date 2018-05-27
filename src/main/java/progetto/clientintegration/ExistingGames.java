package progetto.clientintegration;

import java.util.ArrayList;
import java.util.List;

public final class ExistingGames {

    private static ExistingGames existingGames;

    private ArrayList<ClientGame> clientGameArrayList = new ArrayList<>();

    public static synchronized ExistingGames getExistingGames() {
        if(existingGames == null){

            existingGames = new ExistingGames();

        }

        return existingGames;
    }

    public List<ClientGame> getExistingGamesList(){

        return clientGameArrayList;

    }

    public void addClientGame(ClientGame clientGame){

        clientGameArrayList.add(clientGame);

    }


    private ExistingGames(){

        //

    }

}
