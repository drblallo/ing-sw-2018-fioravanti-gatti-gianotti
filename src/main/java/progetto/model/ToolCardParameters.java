package progetto.model;

import java.io.Serializable;

/**
 * Immutable class with parameters used for toolCards
 * @author Michele
 */
public final class ToolCardParameters implements Serializable {

	private final int cardPosition;
	private final int token;
	private final int singlePlayerDice;
	private final int pickedDicePosition;
	private final int yPlacedDice;
	private final int xPlacedDice;
	private final int yPlacedDice2;
	private final int xPlacedDice2;
	private final int increaseDecrease;
	private final int value;
	private final int changedDiceDB;
	private final int round;
	private final int rTDicePosition;

	private final Dice dice;


	/**
	 * Constructor
	 */
	public ToolCardParameters()
	{
		cardPosition = -1;
		token = -1;
		singlePlayerDice = -1;
		pickedDicePosition = -1;
		yPlacedDice = -1;
		xPlacedDice = -1;
		yPlacedDice2 = -1;
		xPlacedDice2 = -1;
		increaseDecrease = -1;
		value = -1;
		changedDiceDB = -1;
		round = -1;
		rTDicePosition = -1;

		dice = null;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param toolCardParameters previous toolCardParameters
	 * @param cardPosition position of the used card
	 * @param token number of asked token
	 * @param yPlacedDice placed dice position - y
	 * @param xPlacedDice placed dice position - x
	 * @param yPlacedDice2 placed dice 2 position - y
	 * @param xPlacedDice2 placed dice 2 position - x
	 */
	private ToolCardParameters(ToolCardParameters toolCardParameters, int cardPosition, int token, int yPlacedDice, int xPlacedDice,
	                           int yPlacedDice2, int xPlacedDice2)
	{
		this.cardPosition = cardPosition;
		this.token = token;
		this.singlePlayerDice = toolCardParameters.singlePlayerDice;
		this.pickedDicePosition = toolCardParameters.pickedDicePosition;
		this.yPlacedDice = yPlacedDice;
		this.xPlacedDice = xPlacedDice;
		this.yPlacedDice2 = yPlacedDice2;
		this.xPlacedDice2 = xPlacedDice2;
		this.increaseDecrease = toolCardParameters.increaseDecrease;
		this.value = toolCardParameters.value;
		this.changedDiceDB = toolCardParameters.changedDiceDB;
		this.round = toolCardParameters.round;
		this.rTDicePosition = toolCardParameters.rTDicePosition;

		this.dice = toolCardParameters.dice;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param toolCardParameters previous toolCardParameters
	 * @param singlePlayerDice dice used by single player to use a toolCard
	 * @param increaseDecrease 0 to decrease, 1 to increase
	 */
	private ToolCardParameters(ToolCardParameters toolCardParameters, int singlePlayerDice, int increaseDecrease)
	{
		this.cardPosition = toolCardParameters.cardPosition;
		this.token = toolCardParameters.token;
		this.singlePlayerDice = singlePlayerDice;
		this.pickedDicePosition = toolCardParameters.pickedDicePosition;
		this.yPlacedDice = toolCardParameters.yPlacedDice;
		this.xPlacedDice = toolCardParameters.xPlacedDice;
		this.yPlacedDice2 = toolCardParameters.yPlacedDice2;
		this.xPlacedDice2 = toolCardParameters.xPlacedDice2;
		this.increaseDecrease = increaseDecrease;
		this.value = toolCardParameters.value;
		this.changedDiceDB = toolCardParameters.changedDiceDB;
		this.round = toolCardParameters.round;
		this.rTDicePosition = toolCardParameters.rTDicePosition;

		this.dice = toolCardParameters.dice;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param toolCardParameters previous toolCardParameters
	 * @param pickedDicePosition position of the selected picked dice
	 * @param value chosen value
	 * @param changedDiceDB -1 if dice has not been changed yet
	 * @param round round of the selected dice in round track
	 * @param rTDicePosition position of the selected dice in round of rountrack
	 * @param dice dice used for toolCard
	 */
	private ToolCardParameters(ToolCardParameters toolCardParameters, int pickedDicePosition,
	                           int value, int changedDiceDB, int round, int rTDicePosition, Dice dice)
	{
		this.cardPosition = toolCardParameters.cardPosition;
		this.token = toolCardParameters.token;
		this.singlePlayerDice = toolCardParameters.singlePlayerDice;
		this.pickedDicePosition = pickedDicePosition;
		this.yPlacedDice = toolCardParameters.yPlacedDice;
		this.xPlacedDice = toolCardParameters.xPlacedDice;
		this.yPlacedDice2 = toolCardParameters.yPlacedDice2;
		this.xPlacedDice2 = toolCardParameters.xPlacedDice2;
		this.increaseDecrease = toolCardParameters.increaseDecrease;
		this.value = value;
		this.changedDiceDB = changedDiceDB;
		this.round = round;
		this.rTDicePosition = rTDicePosition;

		this.dice = dice;

	}

	/**
	 * Get card position
	 * @return card position
	 */
	public int getNCard() {
		return cardPosition;
	}

	/**
	 * Get number of asked token
	 * @return
	 */
	public int getToken() {
		return token;
	}

	/**
	 * Get single player selected dice
	 * @return
	 */
	public int getSPDice() {
		return singlePlayerDice;
	}

	/**
	 * get picked dice position
	 * @return dice position
	 */
	public int getNDice() {
		return pickedDicePosition;
	}

	/**
	 * get true if first placed dice has been selected
	 * @return  true if first placed dice has been selected
	 */
	public boolean isFirstDiceSet(){
		return getXPlacedDice() >= 0;
	}

	/**
	 get true if second placed dice has been selected
	 * @return  true if second placed dice has been selected
	 */
	public boolean isSecondDiceSet(){
		return getXPlacedDice2()>=0;
	}

	/**
	 * Get y pos of first selected dice in placed frame
	 * @return y pos of first selected dice in placed frame
	 */
	public int getYPlacedDice() {
		return yPlacedDice;
	}

	/**
	 * x pos of first selected dice in placed frame
	 * @return x pos of first selected dice in placed frame
	 */
	public int getXPlacedDice() {
		return xPlacedDice;
	}

	/**
	 * y pos of second selected dice in placed frame
	 * @return y pos of second selected dice in placed frame
	 */
	public int getYPlacedDice2() {
		return yPlacedDice2;
	}

	/**
	 * x pos of second selected dice in placed frame
	 * @return x pos of selected dice in placed frame
	 */
	public int getXPlacedDice2() {
		return xPlacedDice2;
	}

	/**
	 * Get 0 for increase, 1 for decrease, -1 not set
	 * @return 0 for increase, 1 for decrease, -1 not set
	 */
	public int getIncreaseDecrease() {
		return increaseDecrease;
	}

	/**
	 * Get chosen value
	 * @return chosen value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Get -1 if dice has not been changed
	 * @return -1 if dice has not been changed
	 */
	public int getChangedDiceDB() {
		return changedDiceDB;
	}

	/**
	 * get selected round
	 * @return selected round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * Get position of selected dice in roundTrack
	 * @return
	 */
	public int getNDiceRT() {
		return rTDicePosition;
	}

	/**
	 * get selected dice
	 * @return
	 */
	public Dice getDice() {
		return dice;
	}

	/**
	 * Set value
	 * @param nCard
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setNCard(int nCard)
	{
		return new ToolCardParameters(this, nCard, token, yPlacedDice, xPlacedDice,
				yPlacedDice2, xPlacedDice2);
	}

	/**
	 * Set value
	 * @param token
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setToken(int token)
	{
		return new ToolCardParameters(this, cardPosition, token, yPlacedDice, xPlacedDice,
				yPlacedDice2, xPlacedDice2);
	}

	/**
	 * Set value
	 * @param sPDice
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setSPDice(int sPDice)
	{
		return new ToolCardParameters(this, sPDice, increaseDecrease);
	}

	/**
	 * Set value
	 * @param nDice
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setNDice(int nDice)
	{
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, rTDicePosition, dice);
	}

	/**
	 * Set values
	 * @param xPlacedDice
	 * @param yPlacedDice
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setYXValues(int yPlacedDice, int xPlacedDice)
	{
		return new ToolCardParameters(this, cardPosition, token, yPlacedDice, xPlacedDice,
				yPlacedDice2, xPlacedDice2);
	}

	/**
	 * Set values
	 * @param xPlacedDice2
	 * @param yPlacedDice2
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setYX2Values(int yPlacedDice2, int xPlacedDice2)
	{
		return new ToolCardParameters(this, cardPosition, token, yPlacedDice, xPlacedDice,
				yPlacedDice2, xPlacedDice2);
	}

	/**
	 * Set value
	 * @param increaseDecrease
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setIncreaseDecrease(int increaseDecrease)
	{
		return new ToolCardParameters(this, singlePlayerDice, increaseDecrease);
	}

	/**
	 * Set value
	 * @param value
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setValue(int value)
	{
		return new ToolCardParameters(this, pickedDicePosition, value, changedDiceDB, round, rTDicePosition, dice);
	}

	/**
	 * Set value
	 * @param changedDiceDB
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setChangedDiceDB(int changedDiceDB)
	{
		return new ToolCardParameters(this, pickedDicePosition, value, changedDiceDB, round, rTDicePosition, dice);
	}

	/**
	 * Set value
	 * @param round
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setRound(int round)
	{
		return new ToolCardParameters(this, pickedDicePosition, value, changedDiceDB, round, rTDicePosition, dice);
	}

	/**
	 * Set value
	 * @param nDiceRT
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setNDiceRT(int nDiceRT)
	{
		return new ToolCardParameters(this, pickedDicePosition, value, changedDiceDB, round, nDiceRT, dice);
	}

	/**
	 * Set value
	 * @param dice
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setDice(Dice dice)
	{
		return new ToolCardParameters(this, pickedDicePosition, value, changedDiceDB, round, rTDicePosition, dice);
	}

}

