package progetto.game;

import progetto.utils.AbstractObservable;

/**
 * RoundTrack with advanced dice
 */
public final class RoundTrack extends AbstractObservable<RoundTrackData> {

	private RoundTrackData roundTrackData = new RoundTrackData();

	/**
	 * Get round track data
	 * @return round track data
	 */
	public RoundTrackData getRoundTrackData()
	{
		return roundTrackData;
	}

	/**
	 * Add a dice in position index
	 * @param newDice dice to add
	 * @param index position where add the dice
	 */
	void add(Dice newDice, int index)
	{
		roundTrackData = roundTrackData.add(newDice, index);
		change(roundTrackData);
	}

	/**
	 * Change the dice in round index, position pos. Return the dice previously present in this position
	 * @param index round of the dice to change
	 * @param pos position of the dice to change
	 * @param newDice dice to add
	 * @return changed dice
	 */
	Dice change(int index, int pos, Dice newDice)
	{
		Dice dice = roundTrackData.getDice(index, pos);
		roundTrackData = roundTrackData.change(index, pos, newDice);
		change(roundTrackData);
		return dice;
	}

}