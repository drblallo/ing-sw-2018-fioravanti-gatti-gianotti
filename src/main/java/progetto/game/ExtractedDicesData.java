package progetto.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExtractedDicesData {

	private final List<Dice> extractedDices;

	ExtractedDicesData() {
		ArrayList<Dice> temp = new ArrayList<>();
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	ExtractedDicesData(ExtractedDicesData extractedDicesData, Dice newDice) {
		ArrayList<Dice> temp = new ArrayList<>(extractedDicesData.extractedDices);
		temp.add(newDice);
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	ExtractedDicesData(ExtractedDicesData extractedDicesData, Dice newDice, int index)
	{
		ArrayList<Dice> temp = new ArrayList<>(extractedDicesData.extractedDices);
		temp.remove(index);
		temp.add(index, newDice);
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	ExtractedDicesData(ExtractedDicesData extractedDicesData, int index) {
		ArrayList<Dice> temp = new ArrayList<>(extractedDicesData.extractedDices);
		temp.remove(index);
		this.extractedDices = Collections.unmodifiableList(temp);
	}

	public Dice getDice(int index) {
		if(exists(index))
		{
			return extractedDices.get(index);
		}
		return null;
	}

	ExtractedDicesData addDice(Dice newDice) {
		return new ExtractedDicesData(this, newDice);
	}

	public int getNumberOfDices() {
		return extractedDices.size();
	}

	ExtractedDicesData changeDice(int index, Dice newDice) {
		if(exists(index))
		{
			return new ExtractedDicesData(this, newDice, index);
		}
		return this;
	}

	ExtractedDicesData removeDice(int index) {
		if(exists(index))
		{
			return new ExtractedDicesData(this, index);
		}
		return this;
	}

	public boolean exists(int index) {
		try {
			extractedDices.get(index);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}


}
