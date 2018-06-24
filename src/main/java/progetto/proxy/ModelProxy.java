package progetto.proxy;


import progetto.model.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class ModelProxy implements ObservableModel
{
	private MainBoardProxy mainBoardProxy = new MainBoardProxy();
	private Container<RoundTrackData> roundTrackProxy = new Container<>(new RoundTrackData());
	private Container<RoundInformationData> roundInformationProxy = new Container<>(new RoundInformationData());
	private PlayerBoardProxy[] playerBoardProxy = new PlayerBoardProxy[Model.MAX_NUM_PLAYERS];
	private Container<CommandQueueData> commandQueueProxy = new Container<>(
			new CommandQueueData(new ArrayList<>(), new LinkedList<>()));

	public ModelProxy()
	{
		for (int a = 0; a < playerBoardProxy.length; a++)
			playerBoardProxy[a] = new PlayerBoardProxy();
	}

	@Override
	public Container<CommandQueueData> getCommandQueue() {
		return commandQueueProxy;
	}

	@Override
	public Container<RoundTrackData> getRoundTrack() {
		return roundTrackProxy;
	}

	@Override
	public Container<RoundInformationData> getRoundInformation() {
		return roundInformationProxy;
	}

	@Override
	public PlayerBoardProxy getPlayerBoard(int index) {
		return playerBoardProxy[index];
	}

	@Override
	public MainBoardProxy getMainBoard() {
		return mainBoardProxy;
	}

	public ModelProxy copy()
	{
		ModelProxy copy = new ModelProxy();
		insertInto(copy);
		return copy;
	}

	public void insertInto(ModelProxy destination)
	{
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++) {
			PlayerBoardProxy n = destination.getPlayerBoard(a);
			PlayerBoardProxy o = getPlayerBoard(a);

			n.setData(o);
			n.getDicePlacedFrame().setData(o.getDicePlacedFrame());
			n.getPickedDicesSlot().setData(o.getPickedDicesSlot());
		}
		destination.mainBoardProxy.setData(getMainBoard());
		destination.mainBoardProxy.getExtractedDices().setData(getMainBoard().getExtractedDices());
		destination.roundInformationProxy.setData(getRoundInformation());
		destination.commandQueueProxy.setData(getCommandQueue());
		destination.roundTrackProxy.setData(getRoundTrack());
	}

}
