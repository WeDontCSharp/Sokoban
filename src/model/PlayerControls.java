package model;

import java.util.Optional;

import io.Key;

public class PlayerControls {
	private Key up;
	private Key down;
	private Key left;
	private Key right;
	
	public PlayerControls(Key up, Key down, Key left, Key right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	public Optional<Direction> getControl() {
		if (left.isDown()) {
			return Optional.of(Direction.Left);
		}
		if (right.isDown()) {
			return Optional.of(Direction.Right);
		}
		if (up.isDown()) {
			return Optional.of(Direction.Up);
		}
		if (down.isDown()) {
			return Optional.of(Direction.Down);
		}
		return Optional.empty();
	}
}
