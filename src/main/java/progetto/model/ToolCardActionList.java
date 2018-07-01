package progetto.model;

import progetto.controller.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton
 * List of action for each tool card
 */
public final class ToolCardActionList {

	private static ToolCardActionList instance = null;

	private static final List<List<Class>> list = new ArrayList<>();

	/**
	 * Constructor
	 */
	private ToolCardActionList()
	{
		createList();
	}

	public static synchronized ToolCardActionList getInstance()
	{
		if(instance == null)
		{
			instance = new ToolCardActionList();
		}
		return instance;
	}

	/**
	 * Get list of window frame couples
	 * @param index index of the tool card
	 * @return list of action
	 */
	public List<Class> getList(int index)
	{
		try
		{
			return new ArrayList<>(list.get(index-1));
		}
		catch (IndexOutOfBoundsException e)
		{
			return new ArrayList<>();
		}
	}


	/**
	 * Create list of action
	 */
	private void createList()
	{
		List<Class> actionList;


		actionList = new ArrayList<>();  //toolcard 1
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetIncreaseDecreaseAction.class);
		list.add(actionList);


		actionList = new ArrayList<>();  //toolcard 2
		actionList.add(ToolCardSetPlacedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 3
		actionList.add(ToolCardSetPlacedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 4
		actionList.add(ToolCardSetPlacedDiceAction.class);
		actionList.add(ToolCardSetSecondPlacedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 5
		actionList.add(ToolCardSetPickedDiceAction.class);
		actionList.add(ToolCardSetDiceRoundTrackAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 6
		actionList.add(ToolCardSetPickedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 7
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 8
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 9
		actionList.add(ToolCardSetPickedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 10
		actionList.add(ToolCardSetPickedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 11
		actionList.add(ToolCardSetPickedDiceAction.class);
		list.add(actionList);

		actionList = new ArrayList<>();  //toolcard 12
		actionList.add(ToolCardSetDiceRoundTrackAction.class);
		actionList.add(ToolCardSetPlacedDiceAction.class);
		actionList.add(ToolCardSetSecondPlacedDiceAction.class);
		list.add(actionList);

	}

}
