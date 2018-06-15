package progetto.view.commandline;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamProcessor implements Runnable {

    private BufferedReader bin;
    private BufferedWriter bout;

    private IExecutible comproc;

    private boolean isAlive;
    private boolean isActive = true;

    private StringBuilder read = new StringBuilder();

    private static final Logger LOGGER = Logger.getLogger(StreamProcessor.class.getName());

    public StreamProcessor(Reader rin, Writer rout, IExecutible comproc) {

        this.comproc = comproc;
        bin = new BufferedReader(rin);
        if (rout != null)
			bout = new BufferedWriter(rout);
        isAlive = true;

    }

    public boolean isAlive (){
        return isAlive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

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

    public void run() {
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