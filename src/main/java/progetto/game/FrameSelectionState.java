package progetto.game;

import java.util.ArrayList;
import java.util.List;

/**
 *  Distribution of private cards
 *  Selection of public cards
 *  Loading Window Frame from File
 */
public class FrameSelectionState extends AbstractGameState {

	private static final int N_PUBLIC_CARDS = 3;

	public FrameSelectionState() {
		super("Frame selection");
	}

	@Override
	void apply(Game game) {

		extractPrivateCards(game);

		extractPublicCards(game);

		windowFrameReadAndExtraction(game);

	}

	/**
	 * Extract private objective cards
	 * @param game
	 */
	private void extractPrivateCards(Game game)
	{
		ArrayList<Color> privateCards = new ArrayList<>();
		privateCards.add(Color.YELLOW);
		privateCards.add(Color.RED);
		privateCards.add(Color.BLUE);
		privateCards.add(Color.GREEN);
		privateCards.add(Color.PURPLE);
		AbstractPrivateObjectiveCard[] privateObjectiveCard;
		int nPlayer = game.getMainBoard().getData().getPlayerCount();

		if(nPlayer==1)
		{
			privateObjectiveCard = new AbstractPrivateObjectiveCard[2];

			privateObjectiveCard[0] = new ColorShadesPrivateObjectiveCard(
					privateCards.remove(game.getRNGenerator().getNextInt(privateCards.size())));

			privateObjectiveCard[1] = new ColorShadesPrivateObjectiveCard(
					privateCards.remove(game.getRNGenerator().getNextInt(privateCards.size())));

			game.getPlayerBoard(0).setPrivateObjectiveCard(privateObjectiveCard);

		}
		else
		{
			privateObjectiveCard = new AbstractPrivateObjectiveCard[1];
			for(int i=0; i<nPlayer; i++)
			{
				privateObjectiveCard[0]=new ColorShadesPrivateObjectiveCard(
						privateCards.remove(game.getRNGenerator().getNextInt(privateCards.size())));

				game.getPlayerBoard(i).setPrivateObjectiveCard(privateObjectiveCard);
			}
		}
	}

	/**
	 * Extract public objective cards
	 * @param game
	 */
	private void extractPublicCards(Game game)
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

		AbstractPublicObjectiveCard[] publicObjectiveCards;
		int nPlayer = game.getMainBoard().getData().getPlayerCount();

		if(nPlayer==1)
		{
			publicObjectiveCards = new AbstractPublicObjectiveCard[2];

			publicObjectiveCards[0] = publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size()));

			publicObjectiveCards[1] = publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size()));

			game.getMainBoard().setPublicObjectiveCards(publicObjectiveCards);

		}
		else
		{
			publicObjectiveCards = new AbstractPublicObjectiveCard[N_PUBLIC_CARDS];

			publicObjectiveCards[0] = publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size()));

			publicObjectiveCards[1] = publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size()));

			publicObjectiveCards[2] = publicCards.remove(game.getRNGenerator().getNextInt(publicCards.size()));

			game.getMainBoard().setPublicObjectiveCards(publicObjectiveCards);
		}

	}

	/**
	 * Load window frame from file and extract window frame for every player
	 * @param game
	 */
	private void windowFrameReadAndExtraction(Game game)
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

}
