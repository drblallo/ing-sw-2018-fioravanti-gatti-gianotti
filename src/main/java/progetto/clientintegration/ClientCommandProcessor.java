package progetto.clientintegration;

import progetto.commandline.CommandProcessor;
import progetto.commandline.EchoCommand;
import progetto.commandline.HelpCommand;
import progetto.commandline.ICommand;
import progetto.game.*;

final class ClientCommandProcessor extends CommandProcessor{

    public ClientCommandProcessor(){

        super("Command Processor");

    }

    public void setGame(IExecuibleGame iExecuibleGame){

        for (ICommand c: getList()
             ) {

            deregisterCommand(c);
        }

        registerCommand(new EchoCommand());
        registerCommand(new HelpCommand(this));

        registerCommand(new ActionCommand(StartGameAction.class,iExecuibleGame));
        registerCommand(new ActionCommand(AddWindowFrameCoupleAction.class,iExecuibleGame));
        registerCommand(new ActionCommand(SetPlayerCountAction.class, iExecuibleGame));

        registerCommand(new ActionCommand(SetSeedAction.class, iExecuibleGame));
        registerCommand(new ActionCommand(FrameSetAction.class,iExecuibleGame));
        registerCommand(new ActionCommand(PickDiceAction.class, iExecuibleGame));
        registerCommand(new ActionCommand(PlaceDiceAction.class, iExecuibleGame));
        registerCommand(new ActionCommand(EndTurnAction.class, iExecuibleGame));

    }



    }
