package progetto.model;

public abstract class AbstractToolCardState extends AbstractGameState{

	public AbstractToolCardState(String stateName) {
		super(stateName);
	}

	public abstract boolean isEverythingSet(IModel game);

	public abstract void solve(Game game);

}
