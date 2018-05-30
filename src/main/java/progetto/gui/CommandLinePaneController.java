package progetto.gui;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import progetto.commandline.CommandProcessor;

public class CommandLinePaneController {

    @FXML
    private TextArea answer;

    @FXML
    private TextField commandText;

    private CommandProcessor commandProcessor;

    @FXML
    public void onCommandTextKeyPressed(KeyEvent keyEvent){

            if(keyEvent.getCode() == KeyCode.ENTER){

                sendToCommandLine(commandText.getText());

        }

    }

    public void setCommandProcessor(CommandProcessor commandProcessor){

        this.commandProcessor = commandProcessor;

    }

    public void onSendButtonClicked(){

        sendToCommandLine(commandText.getText());

    }

    private void sendToCommandLine(String command){

        answer.appendText('>' + command +'\n'+ commandProcessor.execute(command)+ "\n\n");
        commandText.clear();

    }

    public void clearTextArea(){

        answer.clear();

    }


}