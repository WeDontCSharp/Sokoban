package skeleton.model;

public enum Direction {
	Up, Down, Left, Right;
	
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

