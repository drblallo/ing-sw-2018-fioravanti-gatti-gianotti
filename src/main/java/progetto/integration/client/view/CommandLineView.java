package progetto.integration.client.view;

import progetto.integration.client.ClientController;
import progetto.view.commandline.StreamProcessor;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CommandLineView extends AbstractView
{

	private final ClientCommandProcessor commandProcessor;
	private final StreamProcessor streamProcessor;

	public CommandLineView(ClientController controller)
	{
		super(controller);
		commandProcessor = new ClientCommandProcessor(controller);
		streamProcessor = new StreamProcessor
				(
				new InputStreamReader(System.in),
				new OutputStreamWriter(System.out),
				commandProcessor
				);

		new Thread(streamProcessor).start();
	}

	@Override
	public void setVisible(boolean visible) {
		streamProcessor.setActive(visible);

	}

	@Override
	public void onGameChanged() {
		commandProcessor.reaload();
	}
}
