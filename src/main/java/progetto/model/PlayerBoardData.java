package progetto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Immutable class with data of PlayerBoard
 */
public final class PlayerBoardData implements Serializable{

	private static final Logger LOGGER = Logger.getLogger(PlayerBoardData.class.getName());

	private final WindowFrame windowFrame;
	private final boolean windowFrameIsSet;
	private final List<AbstractPrivateObjectiveCard> privateObjectiveCards;

	private final int token;

	private final WindowFrameCouple[] extractedWindowFrameCouples;

	private final int score;

	/**
	 * Constructor
	 */
	public PlayerBoardData()
	{
		windowFrame = new WindowFrame();
		extractedWindowFrameCouples = new WindowFrameCouple[2];
		windowFrameIsSet = false;
		ArrayList<AbstractPrivateObjectiveCard> temp = new ArrayList<>();
		privateObjectiveCards = Collections.unmodifiableList(temp);
		token = 0;
		score = 0;
	}

	/**
	 * Constructor to set a value
	 * @param windowFrame windowFrame to set
	 * @param extractedWindowFrameCouples extractedWindowFrameCouples to set
	 */
	private PlayerBoardData(PlayerBoardData playerBoardData, WindowFrame windowFrame, WindowFrameCouple[] extractedWindowFrameCouples, boolean windowFrameIsSet)
	{
		this.windowFrame = windowFrame;
		this.extractedWindowFrameCouples = extractedWindowFrameCouples;
		this.windowFrameIsSet = windowFrameIsSet;

		ArrayList<AbstractPrivateObjectiveCard> temp = new ArrayList<>(playerBoardData.privateObjectiveCards);
		this.privateObjectiveCards = Collections.unmodifiableList(temp);

		this.token = playerBoardData.token;

		this.score = playerBoardData.score;
	}

	/**
	 * Constructor to add a privateObjectiveCard
	 * @param playerBoardData previous playerBoardData
	 * @param privateObjectiveCard to add
	 */
	private PlayerBoardData(PlayerBoardData playerBoardData, AbstractPrivateObjectiveCard privateObjectiveCard)
	{
		this.windowFrame = playerBoardData.windowFrame;
		this.extractedWindowFrameCouples = playerBoardData.extractedWindowFrameCouples;
		this.windowFrameIsSet = playerBoardData.windowFrameIsSet;

		ArrayList<AbstractPrivateObjectiveCard> temp = new ArrayList<>(playerBoardData.privateObjectiveCards);
		temp.add(privateObjectiveCard);
		this.privateObjectiveCards = Collections.unmodifiableList(temp);
		this.token = playerBoardData.token;
		this.score = playerBoardData.score;
	}

	/**
	 * Constructor to set token or/and score
	 * @param token token to set
	 * @param score score to set
	 */
	private PlayerBoardData(PlayerBoardData playerBoardData, int token, int score)
	{
		this.windowFrame = playerBoardData.windowFrame;
		this.extractedWindowFrameCouples = playerBoardData.extractedWindowFrameCouples;
		this.windowFrameIsSet = playerBoardData.windowFrameIsSet;
		ArrayList<AbstractPrivateObjectiveCard> temp = new ArrayList<>(playerBoardData.privateObjectiveCards);
		this.privateObjectiveCards = Collections.unmodifiableList(temp);
		this.token = token;
		this.score = score;
	}

	/**
	 * Get player window frame
	 * @return window frame
	 */
	public WindowFrame getWindowFrame()
	{
		return windowFrame;
	}

	/**
	 * Get player extracted window frame
	 * @return extracted window frame array
	 */
	public WindowFrameCouple[] getExtractedWindowFrameCouplesWindowFrame()
	{
		return extractedWindowFrameCouples;
	}

	/**
	 * Set player window frame
	 * @param windowFrame window frame to set
	 * @return new PlayerBoardData with the window frame set
	 */
	PlayerBoardData setWindowFrame(WindowFrame windowFrame)
	{
		return new PlayerBoardData(this, windowFrame, extractedWindowFrameCouples, true);
	}

	/**
	 * Set player window frame
	 * @param couple couple to set as player window frame
	 * @param side to set as player window frame
	 * @return new PlayerBoardData with the window frame set
	 */
	PlayerBoardData setWindowFrame(int couple, int side)
	{
		if((couple==0 || couple==1) && (side==0 || side==1) && extractedWindowFrameCouples[0]!=null && extractedWindowFrameCouples[1]!=null)
		{
			return new PlayerBoardData(this, extractedWindowFrameCouples[couple].getWindowFrame(side), extractedWindowFrameCouples, true);

		}
		LOGGER.log(Level.SEVERE, "Wrong couple or side");
		return this;
	}

	/**
	 * Set player window frame (empty window frame)
	 * @return new PlayerBoardData with the window frame set
	 */
	PlayerBoardData setEmptyWindowFrame()
	{
		return new PlayerBoardData(this, windowFrame, extractedWindowFrameCouples, true);
	}

	/**
	 * Set ExtractedWindowFrame
	 * @param extractedWindowFrameCouples to set
	 * @return new PlayerBoardData with the window frame set
	 */
	PlayerBoardData setExtractedWindowFrame(WindowFrameCouple[] extractedWindowFrameCouples)
	{
		return new PlayerBoardData(this, windowFrame, extractedWindowFrameCouples, false);
	}

	/**
	 * Get windowFrameIsSet
	 * @return windowFrameIsSet
	 */
	public boolean getWindowFrameIsSet()
	{
		return windowFrameIsSet;
	}

	/**
	 * Get List of private objective cards
	 * @return List of public objective cards
	 */
	List<AbstractPrivateObjectiveCard> getPrivateObjectiveCard()
	{
		return new ArrayList<>(this.privateObjectiveCards);
	}

	/**
	 * Add private objective card
	 * @param privateObjectiveCard to add
	 * @return new PlayerBoardData with added card
	 */
	PlayerBoardData addPrivateObjectiveCard(AbstractPrivateObjectiveCard privateObjectiveCard)
	{
		return new PlayerBoardData(this, privateObjectiveCard);
	}

	/**
	 * Get number of token
	 * @return number of token
	 */
	public int getToken()
	{
		return token;
	}

	/**
	 * Set token
	 * @param token to set
	 * @return new PlayerBoardData with modified value
	 */
	public PlayerBoardData setToken(int token)
	{
		return new PlayerBoardData(this, token, score);
	}

	/**
	 * Set score
	 * @param score to set
	 * @return new PlayerBoardData with modified score
	 */
	public PlayerBoardData setScore(int score)
	{
		return new PlayerBoardData(this, token, score);
	}

	/**
	 * Get score
	 * @return score
	 */
	public int getScore()
	{
		return score;
	}


}
