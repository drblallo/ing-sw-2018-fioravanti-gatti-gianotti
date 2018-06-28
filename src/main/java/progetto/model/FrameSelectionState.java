package progetto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * State used during frame selection
 *  Extraction of private cards
 *  Extraction of public cards
 *  Extraction of tool cards
 *  Extraction of window frames
 */
public class FrameSelectionState extends AbstractGameState {

	public FrameSelectionState() {
		super("Frame selection");
	}

	/**
	 * Execute state-related operations
	 * @param game
	 */
	@Override
	void apply(Model game) {

		extractPrivateCards(game);

		extractPublicCards(game);

		extractToolCards(game);

		extractWindowFrame(game);

	}

	/**
	 * Extract private objective cards
	 * @param game
	 */
	private void extractPrivateCards(Model game)
	{
		ArrayList<GameColor> privateCards = new ArrayList<>();
		privateCards.add(GameColor.YELLOW);
		privateCards.add(GameColor.RED);
		privateCards.add(GameColor.BLUE);
		privateCards.add(GameColor.GREEN);
		privateCards.add(GameColor.PURPLE);
		int nPlayer = game.getMainBoard().getData().getPlayerCount();

		if(nPlayer==1)
		{
			game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(
				privateCards.remove(game.getRNGenerator().getNextInt(privateCards.size()))));

			game.getPlayerBoard(0).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(
					privateCards.remove(game.getRNGenerator().getNextInt(privateCards.size()))));

		}
		else
		{
			for(int i=0; i<nPlayer; i++)
			{
				game.getPlayerBoard(i).addPrivateObjectiveCard(new ColorShadesPrivateObjectiveCard(
						privateCards.remove(game.getRNGenerator().getNextInt(privateCards.size()))));
			}
		}
	}

	/**
	 * Extract public objective cards
	 * @param game
	 */
	private void extractPublicCards(Model game)
	{
		ArrayList<AbstractPublicObjectiveCard> publicCards = new ArrayList<>();
		publicCards.add(new RowsDifferentColorsPublicObjectiveCard());
		publicCards.add(new ColumnsDifferentColorsPublicObjectiveCard());
		publicCards.add(new RowsDifferentValuesPublicObjectiveCard());
		publicCards.add(new ColumnsDifferentValuesPublicObjectiveCard());
		publicCards.add(new ClearShadesPublicObjectiveCard());
		publicCards.add(new MediumShadesPublicObjectiveCard());
		publicCards.add(new DarkShadesPublicObjectiveCard());
		publicCards.add(new DifferentShadesPublicObjectiveCard());
		publicCards.add(new DifferentColorsPublicObjectiveCard());
		publicCards.add(new ColoredDiagonalsPublicObjectiveCard());

		int nPlayer = game.getMainBoard().getData().getPlayerCount();

		game.getMainBoard().addPublicObjectiveCards(publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size())));
		game.getMainBoard().addPublicObjectiveCards(publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size())));


		if(nPlayer != 1)
		{
			game.getMainBoard().addPublicObjectiveCards(publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size())));

		}

	}

	/**
	 * Extract window frame for every player
	 * @param game
	 */
	private void extractWindowFrame(Model game)
	{
		List<WindowFrameCouple> windowFrameCouples = game.getMainBoard().getData().getWindowFrameCouples();
		WindowFrameCouple[] couples;
		RNGenerator rnGenerator = game.getRNGenerator();
		int nPlayer = game.getMainBoard().getData().getPlayerCount();

		if(windowFrameCouples.size()>2*nPlayer)
		{
			for(int i=0; i<nPlayer; i++)
			{
				couples = new WindowFrameCouple[2];
				couples[0]=windowFrameCouples.remove(rnGenerator.getNextInt(windowFrameCouples.size()));
				couples[1]=windowFrameCouples.remove(rnGenerator.getNextInt(windowFrameCouples.size()));
				game.getPlayerBoard(i).setExtractedWindowFrameCouples(couples);
			}
		}
		else
		{
			for(int i=0; i<nPlayer; i++)
			{
				game.getPlayerBoard(i).setEmptyWindowFrame();
			}
			game.setState(new GameStartedState());
		}
	}

	/**
	 * Extract tool cards
	 * @param game
	 */
	private void extractToolCards(Model game)
	{
		ToolCardDeck toolCardDeck = new ToolCardDeck();

		int nPlayer = game.getMainBoard().getData().getPlayerCount();

		if(nPlayer == 1)
		{
			int difficulty = game.getMainBoard().getData().getDifficulty();

			for(int i=0; i<difficulty; i++)
			{
				game.getMainBoard().addToolCard(toolCardDeck.getRandomToolCard(game));
			}
		}
		else
		{
			game.getMainBoard().addToolCard(toolCardDeck.getRandomToolCard(game));
			game.getMainBoard().addToolCard(toolCardDeck.getRandomToolCard(game));
			game.getMainBoard().addToolCard(toolCardDeck.getRandomToolCard(game));

		}

	}


}
