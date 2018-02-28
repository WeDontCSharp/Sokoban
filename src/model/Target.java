package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;

public class Target extends Floor {
	
	private Worker whoPushed;

	public Target(int x, int y) {
		super(x, y);
	}

	public void update() {
		
	}

	public void render(Screen screen) {
		screen.drawSprite(getX(), getY(), 1, 4, SpriteSheet.SHEET);
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
			System.err.println("Crate removed from target.");
		}
		
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Worker w) {
		super.setEntityHere(w);
		w.reachTarget(firstPusher);
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Crate c) {
		super.setEntityHere(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		c.reachTarget(firstPusher);
	}

}
