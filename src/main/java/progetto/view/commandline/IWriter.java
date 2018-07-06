package progetto.view.commandline;

/**
 * used to create lambdas
 * @author Federica
 */
public interface IWriter<T> {
	/**
	 *
	 * @param oldData old data
	 * @param newData new data
	 */
	void write(T oldData, T newData);
}
