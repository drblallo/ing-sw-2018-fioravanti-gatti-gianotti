package progetto;

import progetto.model.AbstractGameAction;
import progetto.model.IModel;
import progetto.model.ObservableModel;
import progetto.network.ClientConnection;
import progetto.network.INetworkClient;
import progetto.network.RoomView;
import progetto.network.ServerStateView;
import progetto.network.rmi.RMIClient;
import progetto.network.socket.SocketClient;
import progetto.network.proxy.ModelProxy;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the main controller that contains all the references to every connection, every proxy model and every view.
 * This class hides the multiplicity of connections, so that every view can be attached to the same containers without
 * being aware of any change.
 */
public class ClientController implements IClientController
{
    private static final Logger LOGGER = Logger.getLogger(ClientController.class.getName());
    private ClientConnection clientGame;
    private final List<ClientConnection> connections = new ArrayList<>();
    private List<AbstractView> views = new ArrayList<>();

    private Callback<String> messageCallback = new Callback<>();
    private IObserver<String> messageObserver = (message -> messageCallback.call(message));

    private Callback<RoomView> roomViewCallback = new Callback<>();
    private IObserver<RoomView> roomViewIObserver = (message -> roomViewCallback.call(message));

    private Callback<ServerStateView> serverViewCallback = new Callback<>();
    private IObserver<ServerStateView> serverStateView = (message -> serverViewCallback.call(message));

    private IObserver<ModelProxy> proxyObserver = this::proxyChanged;
    private final ModelProxy fixedProxy = new ModelProxy();
    private ModelProxy lastModel = new ModelProxy();

    /**
     * set the current game. The current game is the one that will be observed
     * @param index the index of the game that must be set
     */
    public synchronized void setCurrentClientGame(int index)
    {
        setCurrentClientGame(connections.get(index));
    }

    /**
     * set the current game from a connection
     * @param newClientGame the connection that must be set as the current game
     */
    private synchronized void setCurrentClientGame(ClientConnection newClientGame)
    {

        if (clientGame != null) {
            clientGame.getMessageCallback().removeObserver(messageObserver);
            clientGame.getRoomViewCallback().removeObserver(roomViewIObserver);
            clientGame.getServerStateViewCallback().removeObserver(serverStateView);
            clientGame.getActionEndedCallback().removeObserver(proxyObserver);
        }

        clientGame = newClientGame;

        if (clientGame != null)
        {
            LOGGER.log(Level.FINE, "Switiching game to {0}", newClientGame.getRoom().getRoomName());

            clientGame.getMessageCallback().addObserver(messageObserver);
            clientGame.getRoomViewCallback().addObserver(roomViewIObserver);
            clientGame.getServerStateViewCallback().addObserver(serverStateView);
            clientGame.getActionEndedCallback().addObserver(proxyObserver);
        }

        for (AbstractView v : views)
            v.onGameChanged();

        if (clientGame  != null)
			proxyChanged(clientGame.getLastClone());
    }

    /**
     * called when the current connections notify that the game has changed.
     * @param proxy the current state of the proxy, this is the proxy that will be returned by getGame
     */
    private synchronized void proxyChanged(ModelProxy proxy)
    {
        LOGGER.log(Level.INFO, "received new proxy from {0}",clientGame);
        lastModel = proxy;
        proxy.insertInto(fixedProxy);
    }

    /**
     * @return the callback that is called every time a message is received by the current connection
     */
    public synchronized Callback<String> getMessageCallback() {
        return messageCallback;
    }

    /**
     *
     * @return the callback that is called every time the roomview of the current connection is changed
     */
    public synchronized Callback<RoomView> getRoomViewCallback() {
        return roomViewCallback;
    }

    /**
     *
     * @return the callback that is called every time the server state of the current connection is changed
     */
    public synchronized Callback<ServerStateView> getServerViewCallback() {
        return serverViewCallback;
    }

    /**
     *
     * @param action the action that must be sent to the game of the current connection
     */
    @Override
    public synchronized void sendAction(AbstractGameAction action) {
            clientGame.sendSynString(action);
    }

    /**
     * this does nothing, since it is up to the server to evaluate the actions
     */
    @Override
    public synchronized void processAllPendingAction() {
    	//handled by the network
    }

    /**
     * this does nothing, since it is up to the server to evaluate the actions
     */
    @Override
    public synchronized void processAction() {
        //handled by the network
    }

    /**
     *
     * @return the the immutable model that was last received from the server
     */
    public IModel getModel()
    {
        return lastModel;
    }

    /**
     *
     * @return the model that is persistent but not thread safe. You can observer the various part of this if you wish to
     * be notified every time they change. It does ensure that this model contains only between-actions data.
     */
    public ObservableModel getObservable() {
        return fixedProxy;
    }

    /**
     * creates a new connection
     * @param ip the adress of the server
     * @param rmi true if you wish to create a rmi connection
     * @return true if the connection was build correctly. False otherwise. Ensure that will not return
     * until the connection is created
     */
    public synchronized boolean createConnection(String ip, boolean rmi)
    {

        INetworkClient c;
        if (rmi)
            c = new RMIClient(ip, Settings.getSettings().getRmiPort());
        else
            c = new SocketClient(ip, Settings.getSettings().getSocketPort());

        if (!c.isRunning())
            return false;

        ClientConnection conn = new ClientConnection(c, new GameSync());
        conn.fetchServerState();

        connections.add(conn);
        conn.getConnectionClosedCallback().addObserver(game ->
        {
            System.out.println("closed connection");
            connections.remove(game);
            if (clientGame == game)
                setCurrentClientGame(null);
        });
        setCurrentClientGame(conn);

        return true;
    }

    /**
     * add a new view
     * @param view the view to be added
     */
    public synchronized void addView(AbstractView view)
    {
        views.add(view);
    }

    /**
     *
     * @return the state of the current room
     */
    public RoomView getCurrentRoom()
    {
        return clientGame.getRoom();
    }

    /**
     *
     * @return the state of the current server
     */
    public ServerStateView getCurrentServerState()
    {
        return clientGame.getServerState();
    }

    /**
     * Send a message to a player in the current room
     * @param message the message to be sent
     * @param targetID the id of the player that must receive the message
     */
    public void sendPrivateMessage(String message, int targetID)
    {
        clientGame.sendPrivateMessage(message, targetID);
    }

    /**
     * Tries to pick the suggested chair. The server can refuse such proposal
     * @param chairID the id of the chair
     */
    public void pickChair(int chairID)
    {
        clientGame.pickChair(chairID);
    }

    /**
     * Asks the server to resend the state information
     */
    public void fetchCurrentState()
    {
        clientGame.fetchServerState();
    }

    /**
     * tries to create a new room on the server
     * @param gameName the name of the game that must be created
     */
    public void createGame(String gameName)
    {
        clientGame.createGame(gameName);
    }

    /**
     * tries to join a game in the current connection
     * @param roomID the id of the room that you wish to join
     * @param playerName the name with which the player will be know in the room
     */
    public void joinGame(int roomID, String playerName)
    {
        clientGame.joinGame(roomID, playerName);
    }

    /**
     * @param index the number of the connection
     * @return the name of the connection
     */
    public String getNameOfConnection(int index)
    {
        return connections.get(index).getRoom().getRoomName();
    }

    /**
     *
     * @return the current connection count
     */
    public int getConnectionCount()
    {
        return connections.size();
    }

    /**
     *
     * @return the curent chair number
     */
    public int getChair(){return clientGame.getChair();}

    /**
     * Tries to disconnect form the current server
     */
    public synchronized void disconnect()
    {
        clientGame.disconnect();
        connections.remove(clientGame);
        setCurrentClientGame(null);
    }

    /**
     * Closes all connections, closes the process.
     */
    public synchronized void shutDown() {
        for(ClientConnection c : connections){
            c.disconnect();
        }
        System.exit(0);
    }
}
