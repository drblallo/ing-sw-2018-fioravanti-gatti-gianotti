package progetto.model;

import java.util.List;

/**
 * Round information
 */
public final class RoundInformation extends Container<RoundInformationData>
{
	private final ExtractedDices extractedDices = new ExtractedDices();

	/**
	 * Constructor
	 */
	 RoundInformation()
	{
		super(new  RoundInformationData());
	}

	/**
	 * Get extracted dices
	 * @return extractedDices
	 */
	public ExtractedDices getExtractedDices()
	{
		return extractedDices;
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

}
