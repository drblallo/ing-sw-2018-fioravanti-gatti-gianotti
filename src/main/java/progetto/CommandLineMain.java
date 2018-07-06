package progetto;

import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.StreamProcessor;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLineMain {
	public static void main(String[] args){

		Logger.getLogger(ClientMain.class.getPackage().getName()).setLevel(Level.SEVERE);
		Settings.getSettings();
		ClientController controller = new ClientController();
		CommandLineView cl = new CommandLineView(controller, System.out);
		cl.setVisible(true);
		new Thread(cl).start();
		StreamProcessor streamProcessor =
				new StreamProcessor(new InputStreamReader(System.in, Charset.defaultCharset()), null, cl);

		new Thread(streamProcessor).start();
	}
}
