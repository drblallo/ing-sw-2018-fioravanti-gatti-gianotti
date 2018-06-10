package progetto.model;

public class ToolCardState extends AbstractGameState{

	private int toolCardIndex;

	public ToolCardState(int toolCardIndex)
	{
		super("Tool Card State");
		this.toolCardIndex = toolCardIndex;

	}

	public int getIndex()
	{
		return toolCardIndex;

	}

}
