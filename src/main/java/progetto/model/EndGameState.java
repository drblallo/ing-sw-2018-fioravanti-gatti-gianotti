package progetto.model;

public class EndGameState extends AbstractGameState {

	public EndGameState()
	{
		super("End model");
	}

	@Override
	void apply(Model game) {
		//fine gioco, calcolo punteggi
	}
}
