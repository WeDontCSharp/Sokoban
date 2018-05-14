package skeleton.model;

/**
 * Enumeration representing the possible directions the workers can move.
 */
public enum Direction {
	Up, Down, Left, Right;
	
	/**
	 * Converts a Direction to a string.
	 * @param dir The direction.
	 * @return The string the direction is converted to, '?' if unknown.
	 */
	public static char toChar(Direction dir) {
		if (dir == null) {
			return '?';
		}
		switch (dir) {
		case Left: return 'L';
		case Right: return 'R';
		case Up: return 'U';
		case Down: return 'D';
		}
		return '?';
	}
}

