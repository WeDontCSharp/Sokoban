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
		screen.drawSprite(getX(), getY(), 2, 3, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() - 16, 2, 2, SpriteSheet.SHEET);
	}
	
	@Override
	public void fallDown(Worker firstPusher) {
		System.err.println("LifeCrate fell.");
		firstPusher.gainHealth();
	}

}
