package progetto.integration.server;

import progetto.controller.SetSeedAction;
import progetto.integration.GameSync;
import progetto.model.*;
import progetto.network.IEnforce;
import progetto.network.ISync;
import progetto.proxy.*;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A server game is a game structure that will send action to the underlying game and will provide to the room
 * the list of enforce that must be sent to the players
 */
public class ServerGame extends GameSync implements  ISync
{
	private final class DirtyTracker
	{
		public final IEnforce enforce;
		public final int origin;

		private DirtyTracker(IEnforce enforce, int origin)
		{
			this.enforce = enforce;
			this.origin = origin;
		}
	}



	private List<DirtyTracker> dirtyDataItems = new ArrayList<>();

	private Callback<IEnforce> enforceCallback = new Callback<>();
	private IObserver<RoundTrackData> rtdObs = ogg -> addItemEnforce(new DirtyTracker(new RoundTrackEnforce(ogg), -1));
	private IObserver<MainBoardData> mainObs = ogg -> addItemEnforce(new DirtyTracker(new MainBoardReplacementEnforce(ogg), 0));
	private IObserver<PlayerBoardData>[] plbObs = new IObserver[Model.MAX_NUM_PLAYERS];
	private IObserver<CommandQueueData> cmqObs = ogg -> addItemEnforce(new DirtyTracker(new CommandQueueEnforce(ogg), 1));
	private IObserver<PickedDicesSlotData>[] pikcObs = new IObserver[Model.MAX_NUM_PLAYERS];
	private IObserver<DicePlacedFrameData>[] pldObs = new IObserver[Model.MAX_NUM_PLAYERS];
	private IObserver<ExtractedDicesData> extObs  = ogg -> addItemEnforce(new DirtyTracker(new ExtractedDicesEnforce(ogg), 2));
	private IObserver<RoundInformationData> inObs = ogg -> addItemEnforce(new DirtyTracker(new RoundInformationEnforce(ogg), -2));
	private static final int PLAYER_BOARD_OFFSET = 1000;

	/**
	 * Send s to the game, process it and call the enforce callback
	 * @param s must be a game action, otherwise it will fail.
	 */
	@Override
	public void sendItem(Serializable s)
	{
		dirtyDataItems.clear();
		super.sendItem(s);

		for (DirtyTracker f : dirtyDataItems)
			enforceCallback.call(f.enforce);

		enforceCallback.call(new DoneEnforce());
	}

	/**
	 * check if the enforce that must be added is already present, if it is erases the last one.
	 * @param tracker the tracker to be added
	 */
	private void addItemEnforce(DirtyTracker tracker) {

		for (int a = dirtyDataItems.size() - 1; a > 0; a--)
		{
			if (dirtyDataItems.get(a).origin == tracker.origin)
			{
				dirtyDataItems.remove(a);
			}
		}
		dirtyDataItems.add(tracker);
	}

	/**
	 * creates a default server game
	 */
	ServerGame()
	{
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++)
		{
			final int c = a;
			int offset = (1 + c)  * PLAYER_BOARD_OFFSET;
			plbObs[a] = ogg -> addItemEnforce(new DirtyTracker(new PlayerBoardReplacementEnforce(ogg, c), offset));
			pikcObs[a] = ogg -> addItemEnforce(new DirtyTracker(new PickedDicesSlotEnforce(ogg, c), offset + 1));
			pldObs[a] = ogg -> addItemEnforce(new DirtyTracker(new DicePlacedFrameEnforce(ogg, c), offset + 2));
		}
		clear();
	}

	/**
	 * reset this server game
	 */
	public void clear()
	{
		if (getGame() != null)
			detachObservers();

		super.clear();
		attachObservers();

		sendItem(new SetSeedAction(0));

		List<WindowFrameCouple> list = WindowFrameCoupleArray.getInstance().getList();

		for (WindowFrameCouple l : list)
			sendItem(new AddWindowFrameCoupleAction(l));
	}

	/**
	 *
	 * @return the callback that is called every time something changes in the model.
	 */
	@Override
	public Callback<IEnforce> getEnforceCallback() {
		return enforceCallback;
	}

	/**
	 * detach of the observer from the current model
	 */
	private void detachObservers()
	{
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++)
		{
			getGame().getModel().getPlayerBoard(a).removeObserver(plbObs[a]);
			getGame().getModel().getPlayerBoard(a).getDicePlacedFrame().removeObserver(pldObs[a]);
			getGame().getModel().getPlayerBoard(a).getPickedDicesSlot().removeObserver(pikcObs[a]);
		}

		getGame().getModel().getMainBoard().removeObserver(mainObs);
		getGame().getModel().getMainBoard().getExtractedDices().removeObserver(extObs);
		getGame().getModel().getCommandQueue().removeObserver(cmqObs);
		getGame().getModel().getRoundInformation().removeObserver(inObs);
		getGame().getModel().getRoundTrack().removeObserver(rtdObs);
	}

	/**
	 * attach the observers to the current model
	 */
	private void attachObservers()
	{
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++)
		{
			getGame().getModel().getPlayerBoard(a).addObserver(plbObs[a]);
			getGame().getModel().getPlayerBoard(a).getDicePlacedFrame().addObserver(pldObs[a]);
			getGame().getModel().getPlayerBoard(a).getPickedDicesSlot().addObserver(pikcObs[a]);
		}

		getGame().getModel().getMainBoard().addObserver(mainObs);
		getGame().getModel().getMainBoard().getExtractedDices().addObserver(extObs);
		getGame().getModel().getCommandQueue().addObserver(cmqObs);
		getGame().getModel().getRoundTrack().addObserver(rtdObs);
		getGame().getModel().getRoundInformation().addObserver(inObs);
	}

	/**
	 *
	 * @return the list of enforces that must be sent to the new players
	 */
	public List<IEnforce> getNewPlayerEnforces()
	{
		List<IEnforce> enforces = new ArrayList<>();
		enforces.add(new MainBoardReplacementEnforce(getGame().getModel().getMainBoard().getData()));
		enforces.add(new ExtractedDicesEnforce(getGame().getModel().getMainBoard().getExtractedDices().getData()));
		enforces.add(new CommandQueueEnforce(getGame().getModel().getCommandQueue().getData()));
		enforces.add(new RoundTrackEnforce(getGame().getModel().getRoundTrack().getData()));
		enforces.add(new RoundInformationEnforce(getGame().getModel().getRoundInformation().getData()));
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++)
		{
			enforces.add(new PlayerBoardReplacementEnforce(getGame().getModel().getPlayerBoard(a).getData(), a));
			enforces.add(new DicePlacedFrameEnforce(getGame().getModel().getPlayerBoard(a).getDicePlacedFrame().getData(), a));
			enforces.add(new PickedDicesSlotEnforce(getGame().getModel().getPlayerBoard(a).getPickedDicesSlot().getData(), a));
		}
		return enforces;
	}
}
