package progetto.view.commandline;

/**
 * @author Federica
 * @param <T>
 */
public interface IDifferenceChecker<T> {

	boolean areDifferent(T data1, T data2);

}
