package progetto.view.commandline;

/**
 * checks if data1 and data2 are equals
 * @author Federica
 */
public interface IDifferenceChecker<T> {

	/**
	 * checks if data1 and data2 are equals
	 * @param data1 old data
	 * @param data2 new data
	 * @return true if they are different
	 */
	boolean areDifferent(T data1, T data2);

}
