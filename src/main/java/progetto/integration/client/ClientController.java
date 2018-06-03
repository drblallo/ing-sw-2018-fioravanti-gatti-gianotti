package progetto.integration.client;

import progetto.ServerMain;
import progetto.controller.IGameController;
import progetto.integration.GameSync;
import progetto.integration.client.view.AbstractView;
import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.network.ClientConnection;
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

    private ClientConnection clientGame;
    private final List<ClientConnection> connections = new ArrayList<>();
    private List<AbstractView> views = new ArrayList<>();

    private Callback<String> messageCallback = new Callback<>();
    private IObserver<String> messageObserver = (message -> messageCallback.call(message));

    private Callback<RoomView> roomViewCallback = new Callback<>();
    private IObserver<RoomView> roomViewIObserver = (message -> roomViewCallback.call(message));

    private Callback<ServerStateView> serverViewCallback = new Callback<>();
    private IObserver<ServerStateView> serverStateView = (message -> serverViewCallback.call(message));

    public void setCurrentClientGame(int index)
    {
        setCurrentClientGame(connections.get(index));
    }

    private void setCurrentClientGame(ClientConnection clientGame){

        if (clientGame != null) {
            clientGame.getMessageCallback().removeObserver(messageObserver);
            clientGame.getRoomViewCallback().removeObserver(roomViewIObserver);
            clientGame.getServerStateViewCallback().removeObserver(serverStateView);
        }

        this.clientGame = clientGame;

        if (clientGame != null)
        {
        	if (!connections.contains(clientGame))
        	    connections.add(clientGame);
            clientGame.getMessageCallback().addObserver(messageObserver);
            clientGame.getRoomViewCallback().addObserver(roomViewIObserver);
            clientGame.getServerStateViewCallback().addObserver(serverStateView);

        }

        for (AbstractView v : views) {
            v.onGameChanged();
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
		clientGame.sendSynString(action);
    }

    @Override
    public void processAllPendingAction() {
    	//handled by the network
    }

    @Override
    public void processAction() {
        //handled by the network
    }

    public IModel getModel()
    {
        return clientGame.getProxy();
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

        ClientConnection conn = new ClientConnection(c, new GameSync());
        conn.fetchServerState();

        setCurrentClientGame(conn);

        return true;
    }

    public void addView(AbstractView view)
    {
        views.add(view);
    }

    public RoomView getCurrentRoom()
    {
        return clientGame.getRoom();
    }

    public ServerStateView getCurrentServerState()
    {
        return clientGame.getServerState();
    }

    public void sendPrivateMessage(String message, int targetID)
    {
        clientGame.sendPrivateMessage(message, targetID);
    }

    public void pickChair(int chairID)
    {
        clientGame.pickChair(chairID);
    }

    public void fetchCurrentState()
    {
        clientGame.fetchServerState();
    }

    public void createGame(String gameName)
    {
        clientGame.createGame(gameName);
    }

    public void joinGame(int roomID, String playerName)
    {
        clientGame.joinGame(roomID, playerName);
    }

    public String getNameOfConnection(int index)
    {
        return connections.get(index).getRoom().getRoomName();
    }

    public int getConnectionCount()
    {
        return connections.size();
    }

    public void disconnect()
    {
        clientGame.disconnect();
        connections.remove(clientGame);
        setCurrentClientGame(null);
    }
}
