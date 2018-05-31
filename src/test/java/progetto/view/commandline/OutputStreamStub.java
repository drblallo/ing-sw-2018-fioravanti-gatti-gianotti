package progetto.view.commandline;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamStub extends OutputStream {

    private String towrite;

    public OutputStreamStub(){

        towrite = new String();

    }

    public void write(int b) throws IOException {

        towrite = towrite + (char) b;
    }

    public String getString(){

        return towrite;

    }
}
