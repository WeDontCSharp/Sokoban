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
		screen.drawSprite(getX(), getY(), 0, 2, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY(), 1, 2, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() + 8, 0, 3, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY() + 8, 1, 3, SpriteSheet.SHEET);
	}

	public boolean accept(List<Entity> entities, Direction dir) {
		Optional<Entity> here = getEntityHere();
		if (!here.isPresent()) {
			return true;
		}
		else {
			Entity e = here.get();
			return entities.get(entities.size() - 1).push(here.get(), entities, dir);
		}
	}

}
