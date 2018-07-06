package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Immutable class with data of RoundInformation
 * @author Michele
 */
public final class RoundInformationData implements Serializable {

	private final int currentFirstPlayer;
	private final int currentPlayer;
	private final int currentRound;

	private List<Integer> playerQueue;

	private final boolean pickedDice;
	private final boolean usedToolCard;

	private final ToolCardParameters toolCardParameters;

	/**
	 * Constructor
	 */
	public RoundInformationData()
	{
		currentFirstPlayer = 0;
		currentPlayer = 0;
		currentRound = 1;

		ArrayList<Integer> tempQ = new ArrayList<>();
		playerQueue = Collections.unmodifiableList(tempQ);

		pickedDice = false;
		usedToolCard = false;

		toolCardParameters = new ToolCardParameters();

	}

	/**
	 * Constructor to set PlayerQueue List
	 * @param roundInformationData previous roundInformationData
	 * @param playerQueue list to set
	 */
	private RoundInformationData(RoundInformationData roundInformationData, List<Integer> playerQueue)
	{
		ArrayList<Integer> tempQ = new ArrayList<>(playerQueue);
		this.playerQueue = Collections.unmodifiableList(tempQ);

		this.currentFirstPlayer = roundInformationData.currentFirstPlayer;
		this.currentPlayer = roundInformationData.currentPlayer;
		this.currentRound = roundInformationData.currentRound;

		this.usedToolCard = roundInformationData.usedToolCard;
		this.pickedDice = roundInformationData.pickedDice;

		this.toolCardParameters = roundInformationData.toolCardParameters;

	}

	/**
	 * Constructor to set a new value for different attributes
	 * @param roundInformationData previous roundInformationData
	 * @param currentFirstPlayer new currentFirstPlayer value
	 * @param currentPlayer new currentPlayer value
	 * @param currentRound new currentRound value
	 */
	private RoundInformationData(RoundInformationData roundInformationData, int currentFirstPlayer,
	                             int currentPlayer, int currentRound)
	{
		this.currentFirstPlayer = currentFirstPlayer;
		this.currentPlayer = currentPlayer;
		this.currentRound = currentRound;
		
		ArrayList<Integer> tempQ = new ArrayList<>(roundInformationData.playerQueue);
		playerQueue = Collections.unmodifiableList(tempQ);

		this.usedToolCard = roundInformationData.usedToolCard;
		this.pickedDice = roundInformationData.pickedDice;

		this.toolCardParameters = roundInformationData.toolCardParameters;

	}

	/**
	 * Constructor to set pickedDice and usedToolCard
	 * @param roundInformationData previous roundInformationData
	 * @param pickedDice new pickedDice value
	 * @param usedToolCard new usedToolCard value
	 */
	private RoundInformationData(RoundInformationData roundInformationData, boolean pickedDice, boolean usedToolCard)
	{
		this.currentFirstPlayer = roundInformationData.currentFirstPlayer;
		this.currentPlayer = roundInformationData.currentPlayer;
		this.currentRound = roundInformationData.currentRound;

		ArrayList<Integer> tempQ = new ArrayList<>(roundInformationData.playerQueue);
		playerQueue = Collections.unmodifiableList(tempQ);

		this.pickedDice = pickedDice;
		this.usedToolCard = usedToolCard;

		this.toolCardParameters = roundInformationData.toolCardParameters;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param roundInformationData previous roundInformationData
	 * @param toolCardParameters tool card parameters
	 */
	private RoundInformationData(RoundInformationData roundInformationData, ToolCardParameters toolCardParameters)
	{
		this.currentFirstPlayer = roundInformationData.currentFirstPlayer;
		this.currentPlayer = roundInformationData.currentPlayer;
		this.currentRound = roundInformationData.currentRound;

		ArrayList<Integer> tempQ = new ArrayList<>(roundInformationData.playerQueue);
		playerQueue = Collections.unmodifiableList(tempQ);

		this.pickedDice = roundInformationData.pickedDice;
		this.usedToolCard = roundInformationData.usedToolCard;

		this.toolCardParameters = toolCardParameters;

	}

	/**
	 * Get tool card parameters
	 */
	public ToolCardParameters getToolCardParameters()
	{
		return toolCardParameters;
	}

	/**
	 * Set value
	 * @param nCard number of the activated card
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setNCard(int nCard)
	{
		ToolCardParameters parameters = toolCardParameters.setNCard(nCard);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param token token of the activated card
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setToken(int token)
	{
		ToolCardParameters parameters = toolCardParameters.setToken(token);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param sPDice single player dice of the activated card
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setSPDice(int sPDice)
	{
		ToolCardParameters parameters = toolCardParameters.setSPDice(sPDice);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param nDice position of selected picked dice of the activated card
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setNDice(int nDice)
	{
		ToolCardParameters parameters = toolCardParameters.setNDice(nDice);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set values
	 * @param xPlacedDice x pos of the first selected placed dice
	 * @param yPlacedDice y pos of the first selected placed dice
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setYXValues(int yPlacedDice, int xPlacedDice)
	{
		ToolCardParameters parameters = toolCardParameters.setYXValues(yPlacedDice, xPlacedDice);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set values
	 * @param xPlacedDice2 x pos of the second selected placed dice
	 * @param yPlacedDice2 y pos of the second selected placed dice
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setYX2Values(int yPlacedDice2, int xPlacedDice2)
	{
		ToolCardParameters parameters = toolCardParameters.setYX2Values(yPlacedDice2, xPlacedDice2);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param increaseDecrease 0 = increase, 1 = decrease
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setIncreaseDecrease(int increaseDecrease)
	{
		ToolCardParameters parameters = toolCardParameters.setIncreaseDecrease(increaseDecrease);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param value new value of the extracted dice
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setValue(int value)
	{
		ToolCardParameters parameters = toolCardParameters.setValue(value);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param changedDiceDB = -1 if the dice has not been changed
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setChangedDiceDB(int changedDiceDB)
	{
		ToolCardParameters parameters = toolCardParameters.setChangedDiceDB(changedDiceDB);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param round number of the round
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setRound(int round)
	{
		ToolCardParameters parameters = toolCardParameters.setRound(round);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param nDiceRT position of the round in the round track
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setNDiceRT(int nDiceRT)
	{
		ToolCardParameters parameters = toolCardParameters.setNDiceRT(nDiceRT);
		return new RoundInformationData(this, parameters);
	}

	/**
	 * Set value
	 * @param dice dice for tool card 11
	 * @return new RoundInformationData with modified values
	 */
	RoundInformationData setDice(Dice dice)
	{
		ToolCardParameters parameters = toolCardParameters.setDice(dice);
		return new RoundInformationData(this, parameters);
	}
	

	/**
	 * Get current first player
	 * @return current first player
	 */
	public int getCurrentFirstPlayer()
	{
		return currentFirstPlayer;
	}

	/**
	 * Get current player
	 * @return current player
	 */
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}

	/**
	 * Get current round
	 * @return current round
	 */
	public int getCurrentRound()
	{
		return currentRound;
	}

	/**
	 * Set current first player
	 * @param currentFirstPlayer to set
	 * @return new roundInformationData with modified currentFirstPlayer
	 */
	 RoundInformationData setCurrentFirstPlayer(int currentFirstPlayer)
	{
		return new RoundInformationData(this, currentFirstPlayer, currentPlayer , currentRound);
	}

	/**
	 * Set current player
	 * @param currentPlayer to set
	 * @return new  RoundInformationData with modified currentPlayer
	 */
	 RoundInformationData setCurrentPlayer(int currentPlayer)
	{
		return new  RoundInformationData(this, currentFirstPlayer, currentPlayer , currentRound);
	}

	/**
	 * Set current round
	 * @param currentRound to set
	 * @return new  RoundInformationData with modified currentRound
	 */
	 RoundInformationData setCurrentRound(int currentRound)
	{
		return new  RoundInformationData(this, currentFirstPlayer, currentPlayer , currentRound);
	}

	/**
	 * Get next player
	 * @return next player
	 */
	public Integer getNextPlayer()
	{
		if(!playerQueue.isEmpty())
		{
			return playerQueue.get(0);
		}
		return -1;
	}

	/**
	 * Add a player in the queue of the round
	 * @param tempQ player list
	 * @return new  RoundInformationData with modified player queue
	 */
	public  RoundInformationData setPlayerQueue(List<Integer> tempQ)
	{
		return new  RoundInformationData(this, tempQ);
	}

	/**
	 * Get a list of players will play before finish the round
	 * @return list
	 */
	public List<Integer> getRoundPlayerList()
	{
		return new ArrayList<>(playerQueue);
	}

	/**
	 * Get pickedDice
	 * @return pickedDice
	 */
	public boolean getPickedDice()
	{
		return pickedDice;
	}

	/**
	 * Get usedToolCard
	 * @return usedToolCard
	 */
	public boolean getUsedToolCard()
	{
		return usedToolCard;
	}

	/**
	 * Set pickedDice
	 * @return new RoundInformationData with modified value
	 */
	public RoundInformationData setPickedDice(boolean pickedDice)
	{
		return new RoundInformationData(this, pickedDice, usedToolCard);
	}

	/**
	 * Set usedToolCard
	 * @return new RoundInformationData with modified value
	 */
	public RoundInformationData setUsedToolCard(boolean usedToolCard)
	{
		return new RoundInformationData(this, pickedDice, usedToolCard);
	}

	/**
	 * Delete all parameters of tool cards
	 * @return new RoundInformationData with deleted parameters
	 */
	RoundInformationData delParamToolCard()
	{
		return new RoundInformationData(this, new ToolCardParameters());
	}

}
