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
	}

	@Override
	public void setEntityHere(Worker firstPusher, Worker w) {
		super.setEntityHere(w);
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Crate c) {
		super.setEntityHere(c);
		for (Hole h : holes) {
			h.setOpen(true);
			Optional<Entity> here = h.getEntityHere();
			if (here.isPresent()) {
				Entity e = here.get();
				e.fallDown(h.getWhoPushed());
			}
		}
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		for (Hole h : holes) {
			h.setOpen(false);
		}
	}

}
