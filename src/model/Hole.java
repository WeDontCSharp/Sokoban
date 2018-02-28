package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;

public class Hole extends Floor {
	
	private boolean open = false;
	private Worker whoPushed;
	
	public Hole(int x, int y) {
		super(x, y);
	}

	public void update() {
		
	}

	public void render(Screen screen) {
		if (isOpen()) {
			screen.drawSprite(getX(), getY(), 2, 6, SpriteSheet.SHEET);
			screen.drawSprite(getX() + 8, getY(), 3, 6, SpriteSheet.SHEET);
			screen.drawSprite(getX(), getY() + 8, 2, 7, SpriteSheet.SHEET);
			screen.drawSprite(getX() + 8, getY() + 8, 3, 7, SpriteSheet.SHEET);
		}else {
			screen.drawSprite(getX(), getY(), 0, 2, SpriteSheet.SHEET);
			screen.drawSprite(getX() + 8, getY(), 1, 2, SpriteSheet.SHEET);
			screen.drawSprite(getX(), getY() + 8, 0, 3, SpriteSheet.SHEET);
			screen.drawSprite(getX() + 8, getY() + 8, 1, 3, SpriteSheet.SHEET);
		}
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Worker w) {
		super.setEntityHere(w);
		if (open) {
			w.fallDown(firstPusher);
		}
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Crate c) {
		super.setEntityHere(c);
		whoPushed = firstPusher;
		if (open) {
			c.fallDown(firstPusher);
		}
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public Worker getWhoPushed() {
		return whoPushed;
	}

}
