package progetto.model;

import java.io.Serializable;

/**
 * Immutable class with parameters used for toolCards
 */
public final class ToolCardParameters implements Serializable {

	private final int nCard;
	private final int token;
	private final int sPDice;
	private final int nDice;
	private final int yPlacedDice;
	private final int xPlacedDice;
	private final int yPlacedDice2;
	private final int xPlacedDice2;
	private final int increaseDecrease;
	private final int value;
	private final int changedDiceDB;
	private final int round;
	private final int nDiceRT;

	private final Dice dice;


	/**
	 * Constructor
	 */
	public ToolCardParameters()
	{
		nCard = -1;
		token = -1;
		sPDice = -1;
		nDice = -1;
		yPlacedDice = -1;
		xPlacedDice = -1;
		yPlacedDice2 = -1;
		xPlacedDice2 = -1;
		increaseDecrease = -1;
		value = -1;
		changedDiceDB = -1;
		round = -1;
		nDiceRT = -1;

		dice = null;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param toolCardParameters previous toolCardParameters
	 * @param nCard
	 * @param token
	 * @param yPlacedDice
	 * @param xPlacedDice
	 * @param yPlacedDice2
	 * @param xPlacedDice2
	 */
	private ToolCardParameters(ToolCardParameters toolCardParameters, int nCard, int token, int yPlacedDice, int xPlacedDice,
	                           int yPlacedDice2, int xPlacedDice2)
	{
		this.nCard = nCard;
		this.token = token;
		this.sPDice = toolCardParameters.sPDice;
		this.nDice = toolCardParameters.nDice;
		this.yPlacedDice = yPlacedDice;
		this.xPlacedDice = xPlacedDice;
		this.yPlacedDice2 = yPlacedDice2;
		this.xPlacedDice2 = xPlacedDice2;
		this.increaseDecrease = toolCardParameters.increaseDecrease;
		this.value = toolCardParameters.value;
		this.changedDiceDB = toolCardParameters.changedDiceDB;
		this.round = toolCardParameters.round;
		this.nDiceRT = toolCardParameters.nDiceRT;

		this.dice = toolCardParameters.dice;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param toolCardParameters previous toolCardParameters
	 * @param sPDice
	 * @param increaseDecrease
	 */
	private ToolCardParameters(ToolCardParameters toolCardParameters, int sPDice, int increaseDecrease)
	{
		this.nCard = toolCardParameters.nCard;
		this.token = toolCardParameters.token;
		this.sPDice = sPDice;
		this.nDice = toolCardParameters.nDice;
		this.yPlacedDice = toolCardParameters.yPlacedDice;
		this.xPlacedDice = toolCardParameters.xPlacedDice;
		this.yPlacedDice2 = toolCardParameters.yPlacedDice2;
		this.xPlacedDice2 = toolCardParameters.xPlacedDice2;
		this.increaseDecrease = increaseDecrease;
		this.value = toolCardParameters.value;
		this.changedDiceDB = toolCardParameters.changedDiceDB;
		this.round = toolCardParameters.round;
		this.nDiceRT = toolCardParameters.nDiceRT;

		this.dice = toolCardParameters.dice;

	}

	/**
	 * Constructor to set tool card parameters
	 * @param toolCardParameters previous toolCardParameters
	 * @param nDice
	 * @param value
	 * @param changedDiceDB
	 * @param round
	 * @param nDiceRT
	 * @param dice
	 */
	private ToolCardParameters(ToolCardParameters toolCardParameters, int nDice,
	                             int value, int changedDiceDB, int round, int nDiceRT, Dice dice)
	{
		this.nCard = toolCardParameters.nCard;
		this.token = toolCardParameters.token;
		this.sPDice = toolCardParameters.sPDice;
		this.nDice = nDice;
		this.yPlacedDice = toolCardParameters.yPlacedDice;
		this.xPlacedDice = toolCardParameters.xPlacedDice;
		this.yPlacedDice2 = toolCardParameters.yPlacedDice2;
		this.xPlacedDice2 = toolCardParameters.xPlacedDice2;
		this.increaseDecrease = toolCardParameters.increaseDecrease;
		this.value = value;
		this.changedDiceDB = changedDiceDB;
		this.round = round;
		this.nDiceRT = nDiceRT;

		this.dice = dice;

	}

	public int getNCard() {
		return nCard;
	}

	public int getToken() {
		return token;
	}

	public int getSPDice() {
		return sPDice;
	}

	public int getNDice() {
		return nDice;
	}

	public boolean isFirstDiceSet(){
		return getXPlacedDice() >= 0;
	}

	public boolean isSecondDiceSet(){
		return getXPlacedDice2()>=0;
	}

	public int getYPlacedDice() {
		return yPlacedDice;
	}

	public int getXPlacedDice() {
		return xPlacedDice;
	}

	public int getYPlacedDice2() {
		return yPlacedDice2;
	}

	public int getXPlacedDice2() {
		return xPlacedDice2;
	}

	public int getIncreaseDecrease() {
		return increaseDecrease;
	}

	public int getValue() {
		return value;
	}

	public int getChangedDiceDB() {
		return changedDiceDB;
	}

	public int getRound() {
		return round;
	}

	public int getNDiceRT() {
		return nDiceRT;
	}

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
		return new ToolCardParameters(this, nCard, token, yPlacedDice, xPlacedDice,
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
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, nDiceRT, dice);
	}

	/**
	 * Set values
	 * @param xPlacedDice
	 * @param yPlacedDice
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setYXValues(int yPlacedDice, int xPlacedDice)
	{
		return new ToolCardParameters(this, nCard, token, yPlacedDice, xPlacedDice,
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
		return new ToolCardParameters(this, nCard, token, yPlacedDice, xPlacedDice,
				yPlacedDice2, xPlacedDice2);
	}

	/**
	 * Set value
	 * @param increaseDecrease
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setIncreaseDecrease(int increaseDecrease)
	{
		return new ToolCardParameters(this, sPDice, increaseDecrease);
	}

	/**
	 * Set value
	 * @param value
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setValue(int value)
	{
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, nDiceRT, dice);
	}

	/**
	 * Set value
	 * @param changedDiceDB
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setChangedDiceDB(int changedDiceDB)
	{
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, nDiceRT, dice);
	}

	/**
	 * Set value
	 * @param round
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setRound(int round)
	{
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, nDiceRT, dice);
	}

	/**
	 * Set value
	 * @param nDiceRT
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setNDiceRT(int nDiceRT)
	{
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, nDiceRT, dice);
	}

	/**
	 * Set value
	 * @param dice
	 * @return new ToolCardParameters with modified values
	 */
	ToolCardParameters setDice(Dice dice)
	{
		return new ToolCardParameters(this, nDice, value, changedDiceDB, round, nDiceRT, dice);
	}

}

