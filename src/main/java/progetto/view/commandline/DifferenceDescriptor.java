package progetto.view.commandline;

import progetto.model.Container;
import progetto.utils.IObserver;

import java.io.Serializable;

public class DifferenceDescriptor<T extends Serializable> {

	private T oldData;
	private Container<T> container;
	private final IWriter<T> writer;
	private final IDifferenceChecker<T> checker;
	private final IObserver<T> observer;

	public DifferenceDescriptor(Container<T> container, IDifferenceChecker<T> checker, IWriter<T> writer)
	{

		oldData = container.getData();
		this.container = container;
		this.writer = writer;
		this.checker = checker;
		observer = data ->
		{
			if (this.checker.areDifferent(oldData, data))
				System.out.println(this.writer.write(oldData, data));

			oldData = data;
		};
		container.addObserver(observer);
	}

	public void detach()
	{
		container.removeObserver(observer);
	}

	public void attach(Container<T> c)
	{
		container.removeObserver(observer);
		c.addObserver(observer);
		oldData = null;
	}

}
