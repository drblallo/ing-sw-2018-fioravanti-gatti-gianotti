package progetto.commandline;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamProcessor implements Runnable {

    private BufferedReader bin;
    private BufferedWriter bout;

    private CommandProcessor comproc;

    private boolean isAlive;

    private StringBuilder read = new StringBuilder();

    private static final Logger LOGGER = Logger.getLogger(StreamProcessor.class.getName());

    public StreamProcessor(Reader rin, Writer rout, CommandProcessor comproc) {

        this.comproc = comproc;
        bin = new BufferedReader(rin);
        bout = new BufferedWriter(rout);
        isAlive = true;

    }

    public boolean isAlive (){

        return isAlive;

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

        if(c=='\t') {

            if(!read.toString().contains(" "))
            {
                characterTab();
            }
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

        output = comproc.processCommand(read.toString()) + '\n';

        bout.write(output);
        bout.flush();

        read.delete(0, read.length()-1);

    }

    private void characterTab() throws IOException {

        List<ICommand> commands = comproc.getList();

        if(!commands.isEmpty()) {

            for (int i = 0; i < commands.size(); i++) {

                if(commands.get(i).getName().startsWith(read.toString())) {

                    bout.write(commands.get(i).getName() + '\n');
                }
            }
            bout.flush();
        }
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