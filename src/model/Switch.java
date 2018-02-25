package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;

public class Switch extends Floor {
	
	List<Hole> holes;

	public Switch(int x, int y) {
		super(x, y);
		holes = new ArrayList<Hole>();
	}

	public void update() {
		
	}

	public void render(Screen screen) {
		screen.drawSprite(getX(), getY(), 2, 4, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY(), 3, 4, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() + 8, 2, 5, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY() + 8, 3, 5, SpriteSheet.SHEET);
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
		/*((Worker)ents.get(0)).gainPoint();
		whoPushed = ((Worker)ents.get(0));
		System.err.println("Crate on target.");*/
		for (Hole h: holes) {
			h.setOpen(true);
		}
		return true;
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		for (Hole h: holes) {
			h.setOpen(false);
		}
		/*if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
			System.err.println("Crate removed from target.");
		}*/
		
	}

}
