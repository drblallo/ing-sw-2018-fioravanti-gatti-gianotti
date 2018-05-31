package progetto.integration.client;

import java.util.ArrayList;
import java.util.List;

public final class ExistingGames {

    private ArrayList<ClientGame> clientGameArrayList = new ArrayList<>();

    public List<ClientGame> getExistingGamesList(){

        return clientGameArrayList;

    }

    public void addClientGame(ClientGame clientGame){

        clientGameArrayList.add(clientGame);

    }

}
