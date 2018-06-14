package progetto.integration.client.view.cl;

import progetto.controller.PlaceDiceAction;

public class PlaceDiceCommand extends AbstractStateSwitcherCommand {

    private int numberOfCommand;

    public PlaceDiceCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView, new RoundViewState(commandLineView));
        this.numberOfCommand = numberOfCommand;
    }

    @Override
    protected void perform(String[] params) {

        if(params.length == 3)
            try {
                int nDice = Integer.parseInt(params[0]);
                int posX = Integer.parseInt(params[1]);
                int posY = Integer.parseInt(params[2]);
                getController().sendAction(new PlaceDiceAction(getController().getChair(), nDice, posX, posY));
                write("Dado piazzato!");
            } catch (NumberFormatException e) {
                write("Digitare una posizione corretta!");
            }
        else
			write("Digitare una posizione corretta!");
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Posiziona un dado: (Formato: 3 <Numero del dado> <Posizione x> <Posizione y>";
    }
}
