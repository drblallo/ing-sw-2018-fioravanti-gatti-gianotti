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


public class ServerGame extends GameSync implements  ISync
{
	private class DirtyTracker
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
	@Override
	public void sendItem(Serializable s)
	{
		dirtyDataItems.clear();
		super.sendItem(s);

		for (DirtyTracker f : dirtyDataItems)
			enforceCallback.call(f.enforce);
	}

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

	ServerGame()
	{
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++)
		{
			final int c = a;
			plbObs[a] = ogg -> addItemEnforce(new DirtyTracker(new PlayerBoardReplacementEnforce(ogg, c), c + 3));
			pikcObs[a] = ogg -> addItemEnforce(new DirtyTracker(new PickedDicesSlotEnforce(ogg, c), c + 3));
			pldObs[a] = ogg -> addItemEnforce(new DirtyTracker(new DicePlacedFrameEnforce(ogg, c), c + 3));
		}
		clear();
	}

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

	@Override
	public Callback<IEnforce> getEnforceCallback() {
		return enforceCallback;
	}

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
		getGame().getModel().getRoundTrack().removeObserver(rtdObs);
	}

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
	}

	public List<IEnforce> getNewPlayerEnforces()
	{
		List<IEnforce> enforces = new ArrayList<>();
		enforces.add(new MainBoardReplacementEnforce(getGame().getModel().getMainBoard().getData()));
		enforces.add(new ExtractedDicesEnforce(getGame().getModel().getMainBoard().getExtractedDices().getData()));
		enforces.add(new CommandQueueEnforce(getGame().getModel().getCommandQueue().getData()));
		enforces.add(new RoundTrackEnforce(getGame().getModel().getRoundTrack().getData()));
		for (int a = 0; a < Model.MAX_NUM_PLAYERS; a++)
		{
			enforces.add(new PlayerBoardReplacementEnforce(getGame().getModel().getPlayerBoard(a).getData(), a));
			enforces.add(new DicePlacedFrameEnforce(getGame().getModel().getPlayerBoard(a).getDicePlacedFrame().getData(), a));
			enforces.add(new PickedDicesSlotEnforce(getGame().getModel().getPlayerBoard(a).getPickedDicesSlot().getData(), a));
		}
		return enforces;
	}
}
