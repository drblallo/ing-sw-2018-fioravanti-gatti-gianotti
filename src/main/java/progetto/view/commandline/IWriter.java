package progetto.view.commandline;

/**
 * @author Federica
 * @param <T>
 */
public interface IWriter<T> {
	void write(T oldData, T newData);
}
