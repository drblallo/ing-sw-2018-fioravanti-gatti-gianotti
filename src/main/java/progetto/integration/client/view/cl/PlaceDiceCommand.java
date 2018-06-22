package progetto.integration.client.view.cl;

import progetto.controller.PlaceDiceAction;

public class PlaceDiceCommand extends AbstractStateSwitcherCommand {


    public PlaceDiceCommand(CommandLineView commandLineView) {
        super(commandLineView, new RoundViewState(commandLineView));
    }

    @Override
    protected void perform(String[] params) {

        String toReturn = "Digitare una posizione valida!";

        if(params.length == 3)
            try {
                int nDice = Integer.parseInt(params[0]);
                int posX = Integer.parseInt(params[1]);
                int posY = Integer.parseInt(params[2]);

                PlaceDiceAction placeDiceAction = new PlaceDiceAction(getController().getChair(), nDice, posX, posY);
                if(placeDiceAction.canBeExecuted(getModel())){
                    getController().sendAction(placeDiceAction);
                    write("Dado piazzato!");
                }
                else write(toReturn);
            } catch (NumberFormatException e) {
                write(toReturn);
            }
        else
			write(toReturn);
    }

    @Override
    public String getHelp() {
        return "Posiziona un dado: (Formato: 3 <Numero del dado> <Posizione x> <Posizione y>)";
    }
}
