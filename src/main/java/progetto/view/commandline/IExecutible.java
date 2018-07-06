package progetto.view.commandline;

/**
 * Use to communicate with stream processor
 * @author Federica
 */
public interface IExecutible {
	/**
	 *
	 * @param params input
	 * @return evaluated string
	 */
	String execute(String params);
}
