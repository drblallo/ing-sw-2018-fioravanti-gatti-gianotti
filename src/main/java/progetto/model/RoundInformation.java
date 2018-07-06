package progetto.model;

import java.util.List;

/**
 * Round information
 * @author Michele
 */
public final class RoundInformation extends Container<RoundInformationData>
{

	/**
	 * Constructor
	 */
	 RoundInformation()
	{
		super(new  RoundInformationData());
	}

	/**
	 * Set current first player
	 * @param currentFirstPlayer new current first player
	 */
	public void setCurrentFirstPlayer(int currentFirstPlayer)
	{
		setData(getData().setCurrentFirstPlayer(currentFirstPlayer));
	}

	/**
	 * Set current player
	 * @param currentPlayer new current player
	 */
	public void setCurrentPlayer(int currentPlayer)
	{
		setData(getData().setCurrentPlayer(currentPlayer));
	}

	/**
	 * Set current round
	 * @param currentRound new current round
	 */
	public void setCurrentRound(int currentRound)
	{
		setData(getData().setCurrentRound(currentRound));
	}

	/**
	 * Remove a player from the queue and add it in first position
	 * @param player id of the player to remove and to add in first position
	 */
	public void changeNextPlayer(Integer player)
	{
		List<Integer> playerQueue = getData().getRoundPlayerList();
		for(int i=0; i<playerQueue.size(); i++)
		{
			if(playerQueue.get(i).equals(player))
			{
				playerQueue.remove(i);
				playerQueue.add(0, player);
				setData(getData().setPlayerQueue(playerQueue));
				return;
			}
		}

	}

	/**
	 * Remove next player
	 */
	public void removeNextPlayer()
	{
		List<Integer> playerQueue = getData().getRoundPlayerList();
		if(!playerQueue.isEmpty())
		{
			playerQueue.remove(0);
			setData(getData().setPlayerQueue(playerQueue));
		}

	}

	/**
	 * Add a player in the queue of the round
	 * @param i next player in round player queue
	 */
	public void addPlayerQueue(Integer i)
	{
		List<Integer> playerQueue = getData().getRoundPlayerList();
		playerQueue.add(i);
		setData(getData().setPlayerQueue(playerQueue));
	}

	/**
	 * Set picked dice
	 * @param pickedDice new value
	 */
	public void setPickedDice(boolean pickedDice)
	{
		setData(getData().setPickedDice(pickedDice));
	}

	/**
	 * Set used toolCard
	 * @param usedToolCard new value
	 */
	public void setUsedToolCard(boolean usedToolCard)
	{
		setData(getData().setUsedToolCard(usedToolCard));
	}

	/**
	 * Set value
	 * @param nCard position of the card
	 */
	public void setNCard(int nCard)
	{
		setData(getData().setNCard(nCard));
	}

	/**
	 * Set value
	 * @param token number of tokens
	 */
	public void setToken(int token)
	{
		 setData(getData().setToken(token));
	}

	/**
	 * Set value
	 * @param sPDice single player dice
	 */
	public void setSPDice(int sPDice)
	{
		setData(getData().setSPDice(sPDice));
	}

	/**
	 * Set value
	 * @param nDice position of selected picked dice
	 */
	public void setNDice(int nDice)
	{
		setData(getData().setNDice(nDice));
	}

	/**
	 * Set values
	 * @param xPlacedDice x pos of the first selected placed dice
	 * @param yPlacedDice y pos of the first selected placed dice
	 */
	public void setYXValues(int yPlacedDice, int xPlacedDice)
	{
		setData(getData().setYXValues(yPlacedDice, xPlacedDice));
	}

	/**
	 * Set values
	 * @param xPlacedDice2 x pos of the second selected placed dice
	 * @param yPlacedDice2 y pos of the second selected placed dice
	 */
	public void setYX2Values(int yPlacedDice2, int xPlacedDice2)
	{
		setData(getData().setYX2Values(yPlacedDice2, xPlacedDice2));
	}

	/**
	 * Set value
	 * @param increaseDecrease 0 = increase, 1 = decrease
	 */
	public void setIncreaseDecrease(int increaseDecrease)
	{
		setData(getData().setIncreaseDecrease(increaseDecrease));
	}

	/**
	 * Set value
	 * @param value value of the new dice
	 */
	public void setValue(int value)
	{
		setData(getData().setValue(value));
	}

	/**
	 * Set value
	 * @param changedDiceDB = -1 if the dice has not been changed
	 */
	public void setChangedDiceDB(int changedDiceDB)
	{
		setData(getData().setChangedDiceDB(changedDiceDB));
	}

	/**
	 * Set value
	 * @param round number of the round
	 */
	public void setRound(int round)
	{
		setData(getData().setRound(round));
	}

	/**
	 * Set value
	 * @param nDiceRT position of the selected dice of the round track
	 */
	public void setNDiceRT(int nDiceRT)
	{
		setData(getData().setNDiceRT(nDiceRT));
	}

	/**
	 * Set value
	 * @param dice dice used by tool card
	 */
	public void setDice(Dice dice)
	{
		setData(getData().setDice(dice));
	}

	/**
	 * Delete all parameters of tool cards
	 */
	public void delParamToolCard()
	{
		setData(getData().delParamToolCard());
	}

}
