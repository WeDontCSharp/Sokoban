package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;

public class Floor extends Field {

	public Floor(int x, int y) {
		super(x, y);
	}

	public void update() {
		
	}

	public void render(Screen screen) {
		screen.drawSprite(getX(), getY(), 0, 4, SpriteSheet.SHEET);
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Worker w) {
		return true;
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Crate c) {
		return true;
	}

}
