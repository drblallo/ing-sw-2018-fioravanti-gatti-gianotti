package progetto.integration.client.view.cl;

public class PlayerRanking {

    private int numberOfPlayer;
    private int ranking;

    public PlayerRanking(int numberOfPlayer, int ranking){
        this.numberOfPlayer = numberOfPlayer;
        this.ranking = ranking;
    }

    public int getNumberOfPlayer(){
        return numberOfPlayer;
    }

    public int getRanking(){
        return ranking;
    }

}
