package progetto.model;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the class every command must be derived from.
 * every derived class must implement canBeExecuted and execute.
 */
public abstract class AbstractGameAction implements Serializable {
	private final int playerID;
	private static final Logger LOGGER = Logger.getLogger(AbstractGameAction.class.getName());

	/**
	 * constructor that sets the caller id.
	 *
	 * @param callerID the id of the player trying to execute the command.
	 */
	protected AbstractGameAction(int callerID) {
		playerID = callerID;
	}

	public AbstractGameAction()
	{
		playerID = -1;
	}

	/**
	 * @return the name of this class.
	 */
	public final String getName() {
		return getClass().getSimpleName();
	}

	/**
	 * @return the id of the player that is trying to perform this action
	 */
	public final int getCallerID() {
		return playerID;
	}

	/**
	 * @param game the model in which this command should be executed
	 * @return true if the current state of the model allow to use this command
	 */
	public abstract boolean canBeExecuted(Model game);

	/**
	 * @param game the model in which we want to execute this command
	 */
	public abstract void execute(Model game);

	/**
	 *
	 * @return a string containing all the fields name and their value of this objects.
	 */
	public final String getToolTip()
	{
		StringBuilder toBeReturned = new StringBuilder(getName()).append(" ");

		try
		{
			for (Field f : getFields(getClass()))
			{
				f.setAccessible(true);
				toBeReturned.append(f.getName()).append(": ").append(f.get(this).toString()).append(" ");
			}
		}
		catch (IllegalAccessException e)
		{
			LOGGER.log(Level.SEVERE,"Failed to create tooltip for {0}", getClass().getName());
			return "could to create tooltip, something is wrong";
		}
		return toBeReturned.toString();
	}


	/**
	 *
	 * @param cl the class of which the list of fields is required
	 * @return the fields of the provided class
	 */
	private static List<Field> getFields(Class<? extends AbstractGameAction> cl)
	{
		ArrayList<Field> fields = new ArrayList<>();

		Deque<Class> ancenstors = new ArrayDeque<>();
		Class c = cl;
		while (c != AbstractGameAction.class.getSuperclass())
		{
			ancenstors.push(c);
			c = c.getSuperclass();
		}

		while (!ancenstors.isEmpty())
		{
			c = ancenstors.pop();
			for (Field f : c.getDeclaredFields())
			{
				if (!Modifier.isStatic(f.getModifiers()))
					fields.add(f);
			}
		}

		return fields;
	}

	/**
	 *
	 * @param cl the class to be processed
	 * @return the string containing the simple class name and the name of the fields of this class
	 */
	public static String getStructure(Class<? extends AbstractGameAction> cl)
	{
		StringBuilder toBeReturned = new StringBuilder(cl.getSimpleName()+" ");

		for (Field f : getFields(cl))
			toBeReturned.append("<").append(f.getName()).append("> ");

		return toBeReturned.toString();
	}


	/**
	 * creates a action
	 * @param actionClass the class of the action to be created
	 * @param fields the int values with which the object will be populated
	 * @return the created object, null if creation failed
	 */
	public static AbstractGameAction createAction(Class<? extends AbstractGameAction> actionClass, int[] fields)
	{

		AbstractGameAction action;
		try
		{
			action = actionClass.newInstance();

			int a = 0;
			for (Field f : getFields(actionClass))
			{
				f.setAccessible(true);
				f.setInt(action, fields[a]);
				a++;
				if (a >= fields.length)
					break;
			}

		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE,"Failed to instantiate {0}", e.getMessage());
			action = null;
		}
		return action;
	}
}
