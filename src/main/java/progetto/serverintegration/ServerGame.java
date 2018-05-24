package progetto.serverintegration;

import progetto.clientintegration.AbstractGameSync;
import progetto.clientmodelproxy.*;
import progetto.game.*;
import progetto.network.IEnforce;
import progetto.network.ISync;
import progetto.utils.Callback;
import progetto.utils.IObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ServerGame extends AbstractGameSync implements  ISync
{
	private List<IEnforce> dirtyDataItems = new ArrayList<>();

	private Callback<IEnforce> enforceCallback = new Callback<>();
	private IObserver<RoundTrackData> rtdObs = ogg -> addItemEnforce(new RoundTrackEnforce(ogg));
	private IObserver<MainBoardData> mainObs = ogg -> addItemEnforce(new MainBoardReplacementEnforce(ogg));
	private IObserver<PlayerBoardData>[] plbObs = new IObserver[Game.MAX_NUM_PLAYERS];
	private IObserver<CommandQueueData> cmqObs = ogg -> addItemEnforce(new CommandQueueEnforce(ogg));
	private IObserver<PickedDicesSlotData>[] pikcObs = new IObserver[Game.MAX_NUM_PLAYERS];
	private IObserver<DicePlacedFrameData>[] pldObs = new IObserver[Game.MAX_NUM_PLAYERS];
	private IObserver<ExtractedDicesData> extObs  = ogg -> addItemEnforce(new ExtractedDicesEnforce(ogg));


	@Override
	public void sendItem(Serializable s)
	{
		dirtyDataItems.clear();
		super.sendItem(s);

		for (IEnforce f : dirtyDataItems)
			enforceCallback.call(f);
	}

	private void addItemEnforce(IEnforce f) {

		for (int a = 0; a < dirtyDataItems.size(); a++)
		{
			if (dirtyDataItems.get(a).getClass() == f.getClass())
				dirtyDataItems.remove(a);
		}
		dirtyDataItems.add(f);
	}

	ServerGame()
	{
		for (int a = 0; a < Game.MAX_NUM_PLAYERS; a++)
		{
			final int c = a;
			plbObs[a] = ogg -> addItemEnforce(new PlayerBoardReplacementEnforce(ogg, c));
			pikcObs[a] = ogg -> addItemEnforce(new PickedDicesSlotEnforce(ogg, c));
			pldObs[a] = ogg -> addItemEnforce(new DicePlacedFrameEnforce(ogg, c));
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

		List<WindowFrameCouple> list = WindowFrameCoupleArray.getList();

		for (WindowFrameCouple l : list)
			sendItem(new AddWindowFrameCoupleAction(l));
	}

	@Override
	public Callback<IEnforce> getEnforceCallback() {
		return enforceCallback;
	}

	private void detachObservers()
	{
		for (int a = 0; a < Game.MAX_NUM_PLAYERS; a++)
		{
			getGame().getPlayerBoard(a).removeObserver(plbObs[a]);
			getGame().getPlayerBoard(a).getDicePlacedFrame().removeObserver(pldObs[a]);
			getGame().getPlayerBoard(a).getPickedDicesSlot().removeObserver(pikcObs[a]);
		}

		getGame().getMainBoard().removeObserver(mainObs);
		getGame().getMainBoard().getExtractedDices().removeObserver(extObs);
		getGame().getCommandQueue().removeObserver(cmqObs);
		getGame().getRoundTrack().removeObserver(rtdObs);
	}

	private void attachObservers()
	{
		for (int a = 0; a < Game.MAX_NUM_PLAYERS; a++)
		{
			getGame().getPlayerBoard(a).addObserver(plbObs[a]);
			getGame().getPlayerBoard(a).getDicePlacedFrame().addObserver(pldObs[a]);
			getGame().getPlayerBoard(a).getPickedDicesSlot().addObserver(pikcObs[a]);
		}

		getGame().getMainBoard().addObserver(mainObs);
		getGame().getMainBoard().getExtractedDices().addObserver(extObs);
		getGame().getCommandQueue().addObserver(cmqObs);
		getGame().getRoundTrack().addObserver(rtdObs);
	}

	public List<IEnforce> getNewPlayerEnforces()
	{
		List<IEnforce> enforces = new ArrayList<>();
		enforces.add(new MainBoardReplacementEnforce(getGame().getMainBoard().getData()));
		enforces.add(new ExtractedDicesEnforce(getGame().getMainBoard().getExtractedDices().getData()));
		enforces.add(new CommandQueueEnforce(getGame().getCommandQueue().getData()));
		enforces.add(new RoundTrackEnforce(getGame().getRoundTrack().getData()));
		for (int a = 0; a < Game.MAX_NUM_PLAYERS; a++)
		{
			enforces.add(new PlayerBoardReplacementEnforce(getGame().getPlayerBoard(a).getData(), a));
			enforces.add(new DicePlacedFrameEnforce(getGame().getPlayerBoard(a).getDicePlacedFrame().getData(), a));
			enforces.add(new PickedDicesSlotEnforce(getGame().getPlayerBoard(a).getPickedDicesSlot().getData(), a));
		}
		return enforces;
	}
}
