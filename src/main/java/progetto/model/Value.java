package progetto.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum for available values
 * @author Michele
 */
public enum Value {
	ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

	private int v;

	private static Map<Integer, Value> map = new HashMap<>();

	static {
		for (Value value : Value.values()) {
			map.put(value.v, value);
		}
	}

	Value(final int val) { v = val; }

	/**
	 * Get Value from int
	 * @param value int value
	 * @return Value value
	 */
	public static Value valueOf(int value) {
		return map.get(value);
	}

}

