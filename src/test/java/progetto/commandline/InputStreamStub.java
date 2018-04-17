package progetto.commandline;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class InputStreamStub extends InputStream {

    private String toread;

    public InputStreamStub (String toread){

        this.toread=toread;

    }
    public int read() throws IOException {

        if(toread==null){

            throw new IOException();

        }
        if(toread.length()==0){

            return -1;

        }


        char c;
        c = toread.charAt(0);

        if(toread.length()>1){

            toread = toread.substring(1, toread.length());

        }
        else toread = "";

        return c;
    }
}
