package progetto.game;

public final class RoundTrackData {

	public static final int NUMBER_OF_ROUNDS = 10;

	private final NineDices[] dices = new NineDices[NUMBER_OF_ROUNDS];

	RoundTrackData() {}

	private RoundTrackData(RoundTrackData roundTrackData, Dice newDice, int index)
	{
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++)
		{
			if (roundTrackData.dices[i] != null)
			{
				dices[i] = new NineDices(roundTrackData.dices[i]);
			}
		}
		if (dices[index] == null) {
			dices[index] = new NineDices();
		}
		dices[index] = dices[index].addDice(newDice);
	}

	private RoundTrackData(RoundTrackData roundTrackData, Dice newDice, int index, int pos)
	{
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++)
		{
			if (roundTrackData.dices[i] != null)
			{
				dices[i] = new NineDices(roundTrackData.dices[i]);
			}
		}
		NineDices nineDices;
		nineDices = dices[index].changeDice(pos, newDice);
		dices[index] = nineDices;
	}

	RoundTrackData add(Dice newDice, int index) {
		if (index < 0 || index > NUMBER_OF_ROUNDS - 1) {
			return this;
		}
		return new RoundTrackData(this, newDice, index);
	}

	/**
	 * Verify if position index is free
	 */
	public boolean isFree(int index) {
		return (dices[index] == null);
	}

	/**
	 * Return first free position
	 */
	public int firstFreePosition() {
		for (int i = 0; i < NUMBER_OF_ROUNDS; i++) {
			if (dices[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public Dice getDice(int index, int pos)
	{
		if(!isFree(index))
		{
			return dices[index].getDice(pos);
		}
		return null;
	}


	RoundTrackData change(int index, int pos, Dice newDice)
	{
		return new RoundTrackData(this, newDice, index, pos);
	}

}
