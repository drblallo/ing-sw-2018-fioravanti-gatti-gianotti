package progetto.view.commandline;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * read from the input stream and collects a line and sends it to the IExecutible, then prints the results
 * @author Federica
 */
public class StreamProcessor implements Runnable {

    private BufferedReader bin;
    private BufferedWriter bout;

    private IExecutible comproc;

    private boolean isAlive;
    private boolean isActive = true;

    private StringBuilder read = new StringBuilder();

    private static final Logger LOGGER = Logger.getLogger(StreamProcessor.class.getName());

    /**
     * public constructor
     * @param rin reader
     * @param rout writer
     * @param comproc IExecutible
     */
    public StreamProcessor(Reader rin, Writer rout, IExecutible comproc) {

        this.comproc = comproc;
        bin = new BufferedReader(rin);
        if (rout != null)
			bout = new BufferedWriter(rout);
        isAlive = true;

    }

    /**
     *
     * @return true if this thread is alive
     */
    public boolean isAlive (){
        return isAlive;
    }

    /**
     * if isActive is set to false this stream processor will not operate
     * @param active isActive = active
     */
    public void setActive(boolean active)
    {
        isActive = active;
    }

    /**
     * process a character
     * @throws IOException are thrown
     */
    private void processCharacter () throws IOException {
        int i;
        char c;

        i = bin.read();
        c=(char) i;
        if(c=='\n') {
            characterEnter();
            return;
        }
        if(i==-1){
            isAlive=false;
            read.append(-1);
            return;
        }
        read.append(c);
    }

    /**
     * called when enter is pressed
     * @throws IOException are thrown
     */
    private void characterEnter()throws IOException{
        String output;
        if (isActive)
        {
            output = comproc.execute(read.toString()) + '\n';
            if (bout != null)
            {
                bout.write(output);
                bout.flush();
            }
        }
        read.delete(0, read.length());
    }

    /**
     * read from the input stream
     */
    public void run() {
        Thread.currentThread().setName("Stream procesor thread");
        while(isAlive) {
            try {
                processCharacter();
            }
            catch (IOException e){
                LOGGER.log(Level.SEVERE,"IOException");
                isAlive = false;
            }

}
}
}