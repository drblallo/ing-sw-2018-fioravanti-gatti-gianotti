package progetto.game;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoughingForcepsState extends AbstractToolCardState {

	private static final Logger LOGGER = Logger.getLogger(DiceBag.class.getName());

	public RoughingForcepsState()
	{
		super("Roughing Forceps");
	}

	@Override
	public boolean isEverythingSet(Game game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		return map.containsKey("nDice") && map.containsKey("increaseDecrease");
	}

	@Override
	public void solve(Game game)
	{
		Map<String, Integer> map = game.getMainBoard().getData().getParamToolCard();
		int nDice = map.get("nDice");
		int increaseDecrease = map.get("increaseDecrease");

		int currentPlayer = game.getMainBoard().getData().getCurrentPlayer();

		DicePlacementCondition dicePlacementCondition = game.getPlayerBoard(currentPlayer).getPickedDicesSlot().getData()
				.getDicePlacementCondition(nDice);

		if(dicePlacementCondition == null)
		{
			LOGGER.log(Level.SEVERE,"Wrong index");
			game.setState(new RoundState());
			return;
		}

		Dice dice = dicePlacementCondition.getDice();

		if(increaseDecrease == 0)
		{
			dice = dice.increaseValue();
		}
		else
		{
			dice = dice.decreaseValue();
		}

		game.getPlayerBoard(currentPlayer).getPickedDicesSlot().changeDice(nDice, dice);

		game.setState(new RoundState());

	}

}
