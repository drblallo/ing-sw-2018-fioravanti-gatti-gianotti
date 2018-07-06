package progetto.view.commandline;

import progetto.model.Container;
import progetto.utils.IObserver;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Federica
 * @param <T>
 */
public class DifferenceDescriptor<T extends Serializable> {

	private T oldData;
	private Container<T> container;
	private final IWriter<T> writer;
	private final IDifferenceChecker<T> checker;
	private final IObserver<T> observer;
	private static final Logger LOGGER = Logger.getLogger(DifferenceDescriptor.class.getName());

	public DifferenceDescriptor(Container<T> container, IDifferenceChecker<T> checker, IWriter<T> writer)
	{
		LOGGER.log(Level.INFO, "Created descriptor attached to {0}", container);
		oldData = container.getData();
		this.container = container;
		this.writer = writer;
		this.checker = checker;
		observer = data -> {
            if (this.checker.areDifferent(oldData, data))
                this.writer.write(oldData, data);

            oldData = data;
        };
		container.addObserver(observer);
	}

	public void detach()
	{
		LOGGER.log(Level.INFO, "Detached from {0}", container);
		container.removeObserver(observer);
	}

	public void attach(Container<T> c)
	{
		LOGGER.log(Level.INFO, "Attached to {0}", container);
		container.removeObserver(observer);
		c.addObserver(observer);
		oldData = null;
	}

}
