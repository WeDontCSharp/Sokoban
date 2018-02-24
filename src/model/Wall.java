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

	public boolean accept(List<Entity> entities, Direction dir) {
		if (entities.size() == 1) {
			return false;
		}else {
			//return entities.get(entities.size() - 1).hitWall(this, entities);
			return entities.get(entities.size() - 1).visit(this, entities);
		}
	}

	/*public boolean hitBy(Worker worker, List<Entity> ents) {
		return true;
	}

	public boolean hitBy(Crate crate, List<Entity> ents) {
		return false;
	}*/

	public boolean visitBy(Crate c, List<Entity> ents) {
		// TODO Auto-generated method stub
		return false;
	}

}
