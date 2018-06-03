package progetto.view.commandline;

public interface IWriter<T> {
	String write(T oldData, T newData);
}
