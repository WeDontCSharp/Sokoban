package skeleton.model;

/**
 * Nothing represents the value of 1.0,
 * Honey represents the value of 2.0,
 * Oil represents the value of 0.0,
 * as a field's original slipFactor is 1.0.
 */
public enum PlaceableItem {
	Nothing, Honey, Oil;
	
	/**
	 * Converts a PlaceableItem to a string.
	 * @param dir The direction.
	 * @return The string the PlaceableItem is converted to, '?' if unknown.
	 */
	public static char toChar(PlaceableItem item) {
		if (item == null) {
			return '?';
		}
		switch (item) {
		case Nothing: return 'N';
		case Honey: return 'H';
		case Oil: return 'O';
		}
		return '?';
	}
}
