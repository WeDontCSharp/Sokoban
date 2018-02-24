package model;

import java.util.List;

import gfx.Screen;
import gfx.SpriteSheet;
import io.Keyboard;

public class Crate extends Entity {

	public Crate(Grid g, Field f) {
		super(g, f);
	}

	public void updateLogic() {
		
	}

	public void renderImage(Screen screen) {
		screen.drawSprite(getX(), getY(), 2, 2, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY(), 3, 2, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() + 8, 2, 3, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8, getY() + 8, 3, 3, SpriteSheet.SHEET);
	}

	@Override
	public boolean push(Entity e, List<Entity> ents, Direction dir) {
		return e.pushBy(this, ents, dir);
		
	}

	@Override
	public boolean pushBy(Worker w, List<Entity> ents, Direction dir) {
		ents.add(this);
		int dx = 0;
		int dy = 0;
		int sx = 0;
		int sy = 0;
		if (dir == Direction.Right) {
			dx = 16;
			sx = 2;
		}
		else if (dir == Direction.Left) {
			dx = -16;
			sx = -2;
		}
		else if (dir == Direction.Up) {
			dy = -16;
			sy = -2;
		}
		else if (dir == Direction.Down) {
			dy = 16;
			sy = 2;
		}
		Field target = level.getFieldPix(getX() + dx, getY() + dy);
		if (target.accept(ents, dir)) {
			// XXX: Process should set references!
			enqueueProcess(new MoveProcess(this, getX() + dx, getY() + dy, sx, sy));
			super.getField().unsetEntity();
			super.setField(target);
			target.setEntityHere(this);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean pushBy(Crate c, List<Entity> ents, Direction dir) {
		// TODO Auto-generated method stub
		ents.add(this);
		int dx = 0;
		int dy = 0;
		int sx = 0;
		int sy = 0;
		if (dir == Direction.Right) {
			dx = 16;
			sx = 2;
		}
		else if (dir == Direction.Left) {
			dx = -16;
			sx = -2;
		}
		else if (dir == Direction.Up) {
			dy = -16;
			sy = -2;
		}
		else if (dir == Direction.Down) {
			dy = 16;
			sy = 2;
		}
		Field target = level.getFieldPix(getX() + dx, getY() + dy);
		if (target.accept(ents, dir)) {
			// XXX: Process should set references!
			enqueueProcess(new MoveProcess(this, getX() + dx, getY() + dy, sx, sy));
			super.getField().unsetEntity();
			super.setField(target);
			target.setEntityHere(this);
			return true;
		}
		return false;
	}
	
	public boolean hitWall(Wall w, List<Entity> ents) {
		return w.hitBy(this, ents);
		
	}

}
