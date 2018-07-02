package progetto.view.commandline;

import progetto.controller.GameController;
import progetto.IClientController;
import progetto.AbstractView;
import progetto.model.*;
import progetto.network.RoomView;
import progetto.network.ServerStateView;
import progetto.utils.Callback;

import java.util.ArrayList;
import java.util.List;

public class ClientControllerStub implements IClientController{

    private Callback<String> messageCallback = new Callback<>();
    private Callback<RoomView> roomViewCallback = new Callback<>();
    private Callback<ServerStateView> serverStateViewCallback = new Callback<>();
    private GameController controller = new GameController();
    private ArrayList<AbstractView> v = new ArrayList<>();

    public ClientControllerStub()
    {
        List<WindowFrameCouple> list = WindowFrameCoupleArray.getInstance().getList();

        for (WindowFrameCouple l : list)
            sendAction(new AddWindowFrameCoupleAction(l));

    }

    @Override
    public void setCurrentClientGame(int index) {

    }

    @Override
    public Callback<String> getMessageCallback() {
        return messageCallback;
    }

    @Override
    public Callback<RoomView> getRoomViewCallback() {
        return roomViewCallback;
    }

    @Override
    public Callback<ServerStateView> getServerViewCallback() {
        return serverStateViewCallback;
    }

    @Override
    public IModel getModel() {
        return controller.getModel();
    }

    @Override
    public ObservableModel getObservable() {
        return controller.getModel();
    }

    @Override
    public boolean createConnection(String ip, boolean rmi) {
        return ip.equals("good");
    }

    @Override
    public void addView(AbstractView view) {
        v.add(view);
    }

    @Override
    public RoomView getCurrentRoom() {
        return new RoomView("1", 1);
    }

    @Override
    public ServerStateView getCurrentServerState() {
        ServerStateView v =  new ServerStateView();
        v.addRoom("room1", 1, 0);
        v.addRoom("room2", 2, 0);
        return v;
    }

    @Override
    public void sendPrivateMessage(String message, int targetID) {

    }

    @Override
    public void pickChair(int chairID) {
        roomViewCallback.call(new RoomView("room", 3));
    }

    @Override
    public void fetchCurrentState() {

    }

    @Override
    public void createGame(String gameName) {

    }

    @Override
    public void joinGame(int roomID, String playerName) {
        for (AbstractView view: v) {
           view.onGameChanged();
        }
    }

    @Override
    public String getNameOfConnection(int index) {
        return null;
    }

    @Override
    public int getConnectionCount() {
        return 1;
    }

    @Override
    public int getChair() {
        return 0;
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void sendAction(AbstractGameAction action) {
        controller.sendAction(action);
        controller.processAllPendingAction();
    }

    @Override
    public void processAllPendingAction() {

    }

    @Override
    public void processAction() {

    }

    @Override
    public void shutDown() {

    }

    @Override
    public boolean thereIsGame() {
        return false;
    }
}
