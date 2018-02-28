package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;

public class Wall extends Field  {

	public Wall(int x, int y) {
		super(x, y);
	}

	public void update() {
		
	}

	public void render(Screen screen) {
		screen.drawSprite(getX(), getY(), 0, 4, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY(), 0, 5, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() + 8, 1, 4, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY() + 8, 1, 5, SpriteSheet.SHEET);
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Worker w) {
		if (w != firstPusher) {
			w.hitWall();
			return true;
		}
		return false;
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Crate c) {
		return false;
	}

}
