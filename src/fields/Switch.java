package fields;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.Crate;
import entities.Entity;
import entities.Worker;
import gfx.Bitmap;
import gfx.SpriteSheet;
import model.Grid;

public class Switch extends Floor {
	
	List<Hole> holes;

	public Switch(Grid level, int x, int y) {
		super(level, x, y);
		holes = new ArrayList<Hole>();
	}

	public void update() {
		
	}

	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 2, 4);
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
