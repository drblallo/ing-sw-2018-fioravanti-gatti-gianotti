package progetto.view.commandline;

public interface IDifferenceChecker<T> {

	boolean areDifferent(T data1, T data2);

}
