package progetto.view.commandline;

public interface IWriter<T> {
	void write(T oldData, T newData);
}
