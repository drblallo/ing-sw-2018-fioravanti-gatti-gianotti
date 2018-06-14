package progetto.model;

import progetto.controller.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to create Deck of ToolCards.
 * Each cards has also a list of available actions
 */
public class ToolCardDeck {

	private ArrayList<ToolCard> toolCards = new ArrayList<>();

	ToolCardDeck(Model game)
	{
		List<Class> actionList;
		int i=1;
		actionList = getDefaultList(game);
		getCard1List(actionList);
		toolCards.add(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard2List(actionList);
		toolCards.add(new ToolCard("Pennello per Eglomise", "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore", GameColor.BLUE,i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard3List(actionList);
		toolCards.add(new ToolCard("Alesatore per lamina di rame", "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore", GameColor.RED, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard4List(actionList);
		toolCards.add(new ToolCard("Lathekin", "Muovi esattamente due dadi, rispettando tutte le restrizioni di piazzamento", GameColor.YELLOW, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard5List(actionList);
		toolCards.add(new ToolCard("Taglierina circolare", "Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato dei Round", GameColor.GREEN, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard6List(actionList);
		toolCards.add(new ToolCard("Pennello per Pasta Salda", "Dopo aver scelto un dado, tira nuovamente quel dado\n" + "Se non puoi piazzarlo, riponilo nella Riserva", GameColor.PURPLE, i, actionList));
		i++;
		actionList = getDefaultList(game);
		toolCards.add(new ToolCard("Martelletto", "Tira nuovamente tutti i dadi della Riserva\n" + "Questa carta può essera usata solo durante il tuo secondo turno, prima di scegliere il secondo dado", GameColor.BLUE, i, actionList));
		i++;
		actionList = getDefaultList(game);
		toolCards.add(new ToolCard("Tenaglia a Rotelle", "Dopo il tuo primo turno scegli immediatamente un altro dado\n" + "Salta il tuo secondo turno in questo round", GameColor.RED, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard9List(actionList);
		toolCards.add(new ToolCard("Riga in Sughero", "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado\n" + "Devi rispettare tutte le restrizioni di piazzamento", GameColor.YELLOW, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard10List(actionList);
		toolCards.add(new ToolCard("Tampone Diamantato", "Dopo aver scelto un dado, giralo sulla faccia opposta", GameColor.GREEN, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard11List(actionList);
		toolCards.add(new ToolCard("Diluente per Pasta Salda", "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal Sacchetto\n" + "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento", GameColor.PURPLE, i, actionList));
		i++;
		actionList = getDefaultList(game);
		getCard12List(actionList);
		toolCards.add(new ToolCard("Taglierina Manuale", "Muovi fino a due dadi dello stesso colore di un solo dado sul Tracciato dei Round\n" + "Devi rispettare tutte le restrizioni di piazzamento", GameColor.BLUE, i, actionList));

	}

	/**
	 * Get random tool card
	 * @return random tool card
	 */
	public ToolCard getRandomToolCard(Model game)
	{
		return toolCards.remove(game.getRNGenerator().getNextInt(toolCards.size()));

	}

	/**
	 * New list with default action for SinglePlayer
	 * @param game Model
	 * @return actionList
	 */
	private List<Class> getDefaultList(Model game)
	{
		List<Class> actionList = new ArrayList<>();
		if(game.getMainBoard().getData().getPlayerCount()==1)
		{
			actionList.add(ToolCardSetSinglePlayerDiceAction.class);
		}
		actionList.add(ExecuteToolCardAction.class);
		return actionList;

	}

	/**
	 * Get tool card 1 list of action
	 * @param actionList list of action
	 */
	private void getCard1List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);

	}

	/**
	 * Get tool card 2 list of action
	 * @param actionList list of action
	 */
	private void getCard2List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPlacedDiceAction.class);

	}

	/**
	 * Get tool card 3 list of action
	 * @param actionList list of action
	 */
	private void getCard3List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPlacedDiceAction.class);

	}

	/**
	 * Get tool card 4 list of action
	 * @param actionList list of action
	 */
	private void getCard4List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPlacedDiceAction.class);

	}

	/**
	 * Get tool card 5 list of action
	 * @param actionList list of action
	 */
	private void getCard5List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetDiceRoundTrackAction.class);

	}

	/**
	 * Get tool card 6 list of action
	 * @param actionList list of action
	 */
	private void getCard6List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPickedDiceAction.class);

	}

	/**
	 * Get tool card 9 list of action
	 * @param actionList list of action
	 */
	private void getCard9List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPickedDiceAction.class);

	}

	/**
	 * Get tool card 10 list of action
	 * @param actionList list of action
	 */
	private void getCard10List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPickedDiceAction.class);

	}

	/**
	 * Get tool card 11 list of action
	 * @param actionList list of action
	 */
	private void getCard11List(List<Class> actionList)
	{
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetDiceValueAction.class);

	}

	/**
	 * Get tool card 12 list of action
	 * @param actionList list of action
	 */
	private void getCard12List(List<Class> actionList)
	{
		actionList.add(ToolCardSetDiceRoundTrackAction.class);
		actionList.add(ToolCardSetPlacedDiceAction.class);

	}

}
