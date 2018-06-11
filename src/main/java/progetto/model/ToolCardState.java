package progetto.model;

/**
 * State used while the player is using an tool card
 */
public class ToolCardState extends AbstractGameState{

	private int toolCardIndex;

	/**
	 * Constructor
	 * @param toolCardIndex index of the tool card
	 */
	public ToolCardState(int toolCardIndex)
	{
		super("Tool Card State");
		this.toolCardIndex = toolCardIndex;

	}

	/**
	 * get index of the tool card
	 * @return index
	 */
	public int getIndex()
	{
		return toolCardIndex;

	}

}
