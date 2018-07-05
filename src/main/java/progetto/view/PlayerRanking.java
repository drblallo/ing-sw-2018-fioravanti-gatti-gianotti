package progetto.view;

/**
 * class used to easily calculate final scores in multi player game
 */
public class PlayerRanking {

    private int numberOfPlayer;
    private int ranking;

    /**
     * public constructor
     * @param numberOfPlayer number of the player
     * @param ranking ranking of the player
     */
    public PlayerRanking(int numberOfPlayer, int ranking){
        this.numberOfPlayer = numberOfPlayer;
        this.ranking = ranking;
    }

    /**
     *
     * @return number of the player
     */
    public int getNumberOfPlayer(){
        return numberOfPlayer;
    }

    /**
     *
     * @return ranking of the player
     */
    public int getRanking(){
        return ranking;
    }

}
