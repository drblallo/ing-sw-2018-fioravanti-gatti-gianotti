package progetto.game;

public abstract class AbstractToolCardState extends AbstractGameState{

	public AbstractToolCardState(String stateName) {
		super(stateName);
	}

	public abstract boolean isEverythingSet(Game game);

	public abstract void solve(Game game);

}
