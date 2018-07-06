package progetto.model;

import java.util.ArrayList;


/**
 * Class to create Deck of ToolCards.
 * Each cards has also a list of available actions
 * @author Michele
 */
public class ToolCardDeck {

	private ArrayList<ToolCard> toolCards = new ArrayList<>();

	/**
	 * constructor
	 */
	ToolCardDeck()
	{
		int i=1;
		toolCards.add(new ToolCard("Pinza Sgrossatrice", "Dopo aver scelto un dado, aumenta o diminuisci il valore del dado scelto di 1", GameColor.PURPLE ,i));
		i++;
		toolCards.add(new ToolCard("Pennello per Eglomise", "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore", GameColor.BLUE,i));
		i++;
		toolCards.add(new ToolCard("Alesatore per lamina di rame", "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore", GameColor.RED, i));
		i++;
		toolCards.add(new ToolCard("Lathekin", "Muovi esattamente due dadi, rispettando tutte le restrizioni di piazzamento", GameColor.YELLOW, i));
		i++;
		toolCards.add(new ToolCard("Taglierina circolare", "Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato dei Round", GameColor.GREEN, i));
		i++;
		toolCards.add(new ToolCard("Pennello per Pasta Salda", "Dopo aver scelto un dado, tira nuovamente quel dado\n" + "Se non puoi piazzarlo, riponilo nella Riserva", GameColor.PURPLE, i));
		i++;
		toolCards.add(new ToolCard("Martelletto", "Tira nuovamente tutti i dadi della Riserva\n" + "Questa carta può essera usata solo durante il tuo secondo turno, prima di scegliere il secondo dado", GameColor.BLUE, i));
		i++;
		toolCards.add(new ToolCard("Tenaglia a Rotelle", "Dopo il tuo primo turno scegli immediatamente un altro dado\n" + "Salta il tuo secondo turno in questo round", GameColor.RED, i));
		i++;
		toolCards.add(new ToolCard("Riga in Sughero", "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado\n" + "Devi rispettare tutte le restrizioni di piazzamento", GameColor.YELLOW, i));
		i++;
		toolCards.add(new ToolCard("Tampone Diamantato", "Dopo aver scelto un dado, giralo sulla faccia opposta", GameColor.GREEN, i));
		i++;
		toolCards.add(new ToolCard("Diluente per Pasta Salda", "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal Sacchetto\n" + "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento", GameColor.PURPLE, i));
		i++;
		toolCards.add(new ToolCard("Taglierina Manuale", "Muovi fino a due dadi dello stesso colore di un solo dado sul Tracciato dei Round\n" + "Devi rispettare tutte le restrizioni di piazzamento", GameColor.BLUE, i));

	}

	/**
	 * Get random tool card
	 * @return random tool card
	 */
	public ToolCard getRandomToolCard(Model game)
	{
		return toolCards.remove(game.getRNGenerator().getNextInt(toolCards.size()));

	}

}
