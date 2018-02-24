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
		screen.drawSprite(getX(), getY(), 0, 6, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY(), 1, 6, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() + 8, 0, 7, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY() + 8, 1, 7, SpriteSheet.SHEET);
	}

	public boolean accept(List<Entity> entities, Direction dir) {
		Entity e = entities.get(entities.size() - 1);
		Optional<Entity> here = getEntityHere();
		if (!here.isPresent()) {
			//e.reachTarget(this, entities);
			e.visit(this, entities);
			return true;
		}
		else {
			if (entities.get(entities.size() - 1).push(here.get(), entities, dir)) {
				//e.reachTarget(this, entities);
				e.visit(this, entities);
				return true;
			}
			return false;
		}
	}
	
	/*public boolean reachBy(Worker worker, List<Entity> ents) {
		return true;
	}*/

	/*public boolean reachBy(Crate crate, List<Entity> ents) {
		((Worker)ents.get(0)).gainPoint();
		whoPushed = ((Worker)ents.get(0));
		return true;
	}*/
	
	public boolean visitBy(Crate crate, List<Entity> ents) {
		((Worker)ents.get(0)).gainPoint();
		whoPushed = ((Worker)ents.get(0));
		return true;
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
		}
		
	}

}
