package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;

public class Hole extends Floor {
	
	private boolean open = false;
	
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
	
	public boolean visitBy(Worker w, List<Entity> ents) {
		/*((Worker)ents.get(0)).gainPoint();
		whoPushed = ((Worker)ents.get(0));
		System.err.println("Crate on target.");*/
		if (isOpen()) {
			System.err.println("Worker fell.");
		}
		return true;
	}
	
	public boolean visitBy(Crate c, List<Entity> ents) {
		if (isOpen()) {
			System.err.println("Crate fell.");
		}
		return true;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
