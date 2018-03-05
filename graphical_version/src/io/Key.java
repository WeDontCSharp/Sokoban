package io;

public class Key {
	private boolean down;
	
	public Key() {
		this.down = false;
	}
	
	public void setDown(boolean d) {
		this.down = d;
	}
	
	public boolean isDown() {
		return down;
	}
}
