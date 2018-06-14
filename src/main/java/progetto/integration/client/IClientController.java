package progetto.integration.client;

import progetto.controller.IGameController;
import progetto.integration.client.view.AbstractView;
import progetto.model.IModel;
import progetto.network.RoomView;
import progetto.network.ServerStateView;
import progetto.utils.Callback;

public interface IClientController extends IGameController {

	void setCurrentClientGame(int index);

	Callback<String> getMessageCallback();
	Callback<RoomView> getRoomViewCallback();
	Callback<ServerStateView> getServerViewCallback();

	IModel getModel();
	boolean createConnection(String ip, boolean rmi);
	void addView(AbstractView view);
	RoomView getCurrentRoom();

	ServerStateView getCurrentServerState();
	void sendPrivateMessage(String message, int targetID);
	void pickChair(int chairID);
	void fetchCurrentState();
	void createGame(String gameName);
	void joinGame(int roomID, String playerName);
	String getNameOfConnection(int index);
	int getConnectionCount();
	int getChair();
	void disconnect();
}

