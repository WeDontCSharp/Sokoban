package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;
import io.Keyboard;

public class LifeCrate extends Crate {

	public LifeCrate(Grid g, Field f) {
		super(g, f);
	}

	public void updateLogic() {
		
	}

	public void renderImage(Screen screen) {
		screen.drawSprite(getX(), getY(), 4, 2, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY(), 5, 2, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() + 8, 4, 3, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY() + 8, 5, 3, SpriteSheet.SHEET);
	}
	
	@Override
	public void fallDown(Worker firstPusher) {
		System.err.println("LifeCrate fell.");
		firstPusher.gainHealth();
	}

}
