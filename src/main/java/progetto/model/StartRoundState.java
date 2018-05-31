package progetto.model;

public class StartRoundState extends AbstractGameState{
	public StartRoundState() {
		super("Start round");
	}

	@Override
	void apply(Game game) {

		MainBoard mainBoard = game.getMainBoard();
		int firstPlayer = mainBoard.getData().getCurrentFirstPlayer();
		int nPlayer = mainBoard.getData().getPlayerCount();

		//start player queue
		for(int i=1; i<nPlayer; i++)
		{
			mainBoard.addPlayerQueue((i+firstPlayer)%nPlayer);
		}
		for(int i=nPlayer-1; i>=0; i--)
		{
			mainBoard.addPlayerQueue((i+firstPlayer)%nPlayer);
		}

		//extract dice from diceBag

		if(nPlayer == 1)
		{
			mainBoard.getExtractedDices().addDice(game.getRNGenerator().extractDice(game.getDiceBag()));
		}

		for(int i=0; i<=2*nPlayer; i++)
		{
			mainBoard.getExtractedDices().addDice(game.getRNGenerator().extractDice(game.getDiceBag()));
		}


		game.setState(new RoundState());

	}
}
