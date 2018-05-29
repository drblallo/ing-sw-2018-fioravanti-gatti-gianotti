package progetto.game;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Immutable class with data of PlayerBoard
 */
public final class PlayerBoardData implements Serializable{

	private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

	private final WindowFrame windowFrame;
	private final boolean windowFrameIsSet;
	private final AbstractPrivateObjectiveCard[] privateObjectiveCard;

	private final WindowFrameCouple[] extractedWindowFrameCouples;

	/**
	 * Constructor
	 */
	public PlayerBoardData()
	{
		windowFrame = new WindowFrame();
		extractedWindowFrameCouples = new WindowFrameCouple[2];
		windowFrameIsSet = false;
		privateObjectiveCard = new AbstractPrivateObjectiveCard[0];
	}

	/**
	 * Constructor to set extractedWindowFrameCouples or windowFrame
	 * @param windowFrame windowFrame to set
	 * @param extractedWindowFrameCouples extractedWindowFrameCouples to set
	 */
	private PlayerBoardData(WindowFrame windowFrame, WindowFrameCouple[] extractedWindowFrameCouples, boolean windowFrameIsSet, AbstractPrivateObjectiveCard[] privateObjectiveCard)
	{
		this.windowFrame = windowFrame;
		this.extractedWindowFrameCouples = extractedWindowFrameCouples;
		this.windowFrameIsSet = windowFrameIsSet;
		this.privateObjectiveCard = privateObjectiveCard;
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
		return new PlayerBoardData(windowFrame, extractedWindowFrameCouples, true, privateObjectiveCard);
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
			return new PlayerBoardData(extractedWindowFrameCouples[couple].getWindowFrame(side), extractedWindowFrameCouples, true, privateObjectiveCard);

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
		return new PlayerBoardData(windowFrame, extractedWindowFrameCouples, true, privateObjectiveCard);
	}


	PlayerBoardData setExtractedWindowFrame(WindowFrameCouple[] extractedWindowFrameCouples)
	{
		return new PlayerBoardData(windowFrame, extractedWindowFrameCouples, false, privateObjectiveCard);
	}

	boolean getWindowFrameIsSet()
	{
		return windowFrameIsSet;
	}

	AbstractPrivateObjectiveCard[] getPrivateObjectiveCard()
	{
		return privateObjectiveCard;
	}

	PlayerBoardData setPrivateObjectiveCard(AbstractPrivateObjectiveCard[] privateObjectiveCard)
	{
		return new PlayerBoardData(windowFrame, extractedWindowFrameCouples, windowFrameIsSet, privateObjectiveCard);
	}

}
