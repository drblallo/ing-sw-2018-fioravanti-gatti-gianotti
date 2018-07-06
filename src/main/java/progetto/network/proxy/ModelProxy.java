package progetto.network.proxy;


import progetto.model.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Massimo
 * proxy of the whole model, it is able to clone another observable model so that immutable object can be created.
 */
public class ModelProxy implements ObservableModel
{
	private final MainBoardProxy mainBoardProxy = new MainBoardProxy();
	private final Container<RoundTrackData> roundTrackProxy = new Container<>(new RoundTrackData());
	private final Container<RoundInformationData> roundInformationProxy = new Container<>(new RoundInformationData());
	private final PlayerBoardProxy[] playerBoardProxy = new PlayerBoardProxy[Model.MAX_NUM_PLAYERS];
	private final Container<CommandQueueData> commandQueueProxy = new Container<>(
			new CommandQueueData(new ArrayList<>(), new LinkedList<>()));

	/**
	 * creates a proxy with no data in it
	 */
	public ModelProxy()
	{
		for (int a = 0; a < playerBoardProxy.length; a++)
			playerBoardProxy[a] = new PlayerBoardProxy();
	}

	/**
	 *
	 * @return the command queue proxy
	 */
	@Override
	public Container<CommandQueueData> getCommandQueue() {
		return commandQueueProxy;
	}

	/**
	 *
	 * @return the round track proxy
	 */
	@Override
	public Container<RoundTrackData> getRoundTrack() {
		return roundTrackProxy;
	}

	/**
	 *
	 * @return the round information proxy
	 */
	@Override
	public Container<RoundInformationData> getRoundInformation() {
		return roundInformationProxy;
	}

	/**
	 *
	 * @param index the index of the player board
	 * @return the player board proxy
	 */
	@Override
	public PlayerBoardProxy getPlayerBoard(int index) {
		return playerBoardProxy[index];
	}

	/**
	 *
	 * @return the main board proxy
	 */
	@Override
	public MainBoardProxy getMainBoard() {
		return mainBoardProxy;
	}

	/**
	 *
	 * @return a new copy of this object containing the same data
	 */
	public ModelProxy copy()
	{
		ModelProxy copy = new ModelProxy();
		insertInto(copy);
		return copy;
	}

	/**
	 * set the data of anther model proxy to be the same as this one
	 * @param destination the model that will receive the data
	 */
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
