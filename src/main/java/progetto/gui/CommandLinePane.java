package progetto.gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import progetto.commandline.CommandProcessor;

public class CommandLinePane extends Pane
{
    private TextField commandText;
    private TextArea answer;
    private VBox mainLayout;
    private CommandProcessor ccp;



    public CommandLinePane(CommandProcessor ccp) {

        this.ccp = ccp;

        Button sendButton = new Button("Send");

        answer = new TextArea();
        answer.setEditable(false);

        commandText = new TextField();
        commandText.setPromptText("Insert command");
        commandText.setOnKeyPressed(event ->{

            if(event.getCode() == KeyCode.ENTER){

                sendToCommandLine(commandText.getText());

            }

        });

        sendButton.setOnAction(event -> sendToCommandLine(commandText.getText()));

        HBox layoutO = new HBox(10);
        layoutO.setPadding(new Insets(20,0,20,0));
        layoutO.getChildren().addAll(commandText, sendButton);

        mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20,20,20,20));
        mainLayout.getChildren().addAll(answer, layoutO);
    }

    public VBox getLayout(){

        return mainLayout;
    }

    private void sendToCommandLine(String command){

        answer.appendText('>' + command +'\n'+ ccp.execute(command)+ "\n\n");
        commandText.clear();


    }
}
