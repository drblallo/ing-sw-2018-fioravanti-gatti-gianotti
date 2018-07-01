package progetto.view.commandline.commands;

import progetto.controller.ToolCardSetDiceValueAction;
import progetto.model.ToolCardParameters;
import progetto.view.commandline.CommandLineView;

public class ChooseValueDiceCommand extends AbstractCLViewCommand {

    private static final int MAX_VALUE = 6;

    public ChooseValueDiceCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] args) {

        String toReturn = "Inserire un valore valido!";

        if(args == null || args.length == 0){
            write(toReturn);
            return;
        }
        try {
            int value = Integer.parseInt(args[0]);
            if(value>0 && value <= MAX_VALUE){
                ToolCardSetDiceValueAction toolCardSetDiceValueAction =
                        new ToolCardSetDiceValueAction(getController().getChair(), value);
                if(toolCardSetDiceValueAction.canBeExecuted(getModel())){
                    getController().sendAction(toolCardSetDiceValueAction);
                }
                else write(toReturn);
            }

        }catch (NumberFormatException e ){
            write(toReturn);
        }
    }

    @Override
    public String getHelp(){
        ToolCardParameters toolCardParameters = getModel().getRoundInformation().getData().getToolCardParameters();
        if (toolCardParameters.getDice()!=null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("E' stato estratto un dado di colore ")
                    .append(toolCardParameters.getDice().getGameColor().toString()).append(",");
            if (toolCardParameters.getValue()>0)
                stringBuilder.append(" (Valore attuale: ")
                        .append(toolCardParameters.getDice().getValue().toString()).append(")");
            else stringBuilder.append(" scegli il valore da assegnargli (Foarmato: ").append(getName())
                    .append("<Valore del dado>)");
            return stringBuilder.toString();
        }
        else return "Scegli il valore che vuoi assegnare al dado selezionato (Formato: " + getName()
                + " <Valore del dado>)";
    }
}
