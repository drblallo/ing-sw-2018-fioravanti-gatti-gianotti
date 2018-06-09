package progetto.model;


/**
 * RoundTrack with advanced dice
 */
public final class RoundTrack extends Container<RoundTrackData> {

	RoundTrack()
	{
		super(new RoundTrackData());
	}

	/**
	 * Add a dice in position index
	 * @param newDice dice to add
	 * @param index position where add the dice
	 */
	void add(Dice newDice, int index)
	{
		setData(getData().add(newDice, index));
	}

	/**
	 * Change the dice in round index, position pos. Return the dice previously present in this position
	 * @param index round of the dice to change
	 * @param pos position of the dice to change
	 * @param newDice dice to add
	 * @return changed dice
	 */
	public Dice change(int index, int pos, Dice newDice)
	{
		Dice dice = getData().getDice(index, pos);
		setData(getData().change(index, pos, newDice));
		return dice;
	}

}