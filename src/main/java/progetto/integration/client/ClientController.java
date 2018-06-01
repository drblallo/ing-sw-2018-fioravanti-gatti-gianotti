package progetto.integration.client;

import progetto.ServerMain;
import progetto.controller.IGameController;
import progetto.integration.client.view.IView;
import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.network.INetworkClient;
import progetto.network.RoomView;
import progetto.network.ServerStateView;
import progetto.network.rmi.RMIClient;
import progetto.network.socket.SocketClient;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.util.ArrayList;
import java.util.List;

public class ClientController implements IGameController
{

    private ClientGame clientGame;
    private final ExistingGames existingGames = new ExistingGames();
    private List<IView> views = new ArrayList<>();

    private Callback<String> messageCallback = new Callback<>();
    private IObserver<String> messageObserver = (message -> messageCallback.call(message));

    private Callback<RoomView> roomViewCallback = new Callback<>();
    private IObserver<RoomView> roomViewIObserver = (message -> roomViewCallback.call(message));

    private Callback<ServerStateView> serverViewCallback = new Callback<>();
    private IObserver<ServerStateView> serverStateView = (message -> serverViewCallback.call(message));

    public void setCurrentClientGame(ClientGame clientGame){

        if (clientGame != null) {
            clientGame.getMessageCallback().removeObserver(messageObserver);
            clientGame.getRoomViewCallback().removeObserver(roomViewIObserver);
            clientGame.getServerViewCallback().removeObserver(serverStateView);
        }

        this.clientGame = clientGame;

        if (clientGame != null)
        {
            clientGame.getMessageCallback().addObserver(messageObserver);
            clientGame.getRoomViewCallback().addObserver(roomViewIObserver);
            clientGame.getServerViewCallback().addObserver(serverStateView);

        }

        for (IView v : views) {
            v.setCurrentGame(clientGame);
        }
    }

    public Callback<String> getMessageCallback() {
        return messageCallback;
    }

    public Callback<RoomView> getRoomViewCallback() {
        return roomViewCallback;
    }

    public Callback<ServerStateView> getServerViewCallback() {
        return serverViewCallback;
    }

    @Override
    public void sendAction(AbstractGameAction action) {
            clientGame.sendAction(action);
    }

    @Override
    public void processAllPendingAction() {
        clientGame.processAllPendingAction();
    }

    @Override
    public void processAction() {
        clientGame.processAction();
    }

    public IModel getModel()
    {
        return clientGame.getModel();
    }

    public List<ClientGame> getGames()
    {
    	return existingGames.getExistingGamesList();
    }

    public boolean createConnection(String ip, boolean rmi)
    {

        INetworkClient c;
        if (rmi)
            c = new RMIClient(ip);
        else
            c = new SocketClient(ip, ServerMain.DEFAULT_PORT);

        if (!c.isRunning())
            return false;

        ClientGame conn = new ClientGame(c);
        conn.getClientConnection().fetchServerState();

        existingGames.addClientGame(conn);

        setCurrentClientGame(conn);

        return true;
    }

    public void addView(IView view)
    {
        views.add(view);
        view.setController(this);
    }

    public RoomView getCurrentRoom()
    {
        return clientGame.getClientConnection().getRoom();
    }

    public ServerStateView getCurrentServerState()
    {
        return clientGame.getClientConnection().getServerState();
    }

    public void sendPrivateMessage(String message, int targetID)
    {
        clientGame.getClientConnection().sendPrivateMessage(message, targetID);
    }

    public void pickChair(int chairID)
    {
        clientGame.getClientConnection().pickChair(chairID);
    }

    public void fetchCurrentState()
    {
        clientGame.getClientConnection().fetchServerState();
    }

    public void createGame(String gameName)
    {
        clientGame.getClientConnection().createGame(gameName);
    }

    public void joinGame(int roomID, String playerName)
    {
        clientGame.getClientConnection().joinGame(roomID, playerName);
    }
}
