package model;

import java.util.ArrayList;
import java.util.List;

import gfx.Screen;
import gfx.SpriteSheet;
import io.Keyboard;

public class Worker extends Entity {

	private Direction direction;
	private boolean dummy;
	private int points;
	
	public Worker(Grid g, Field f, Direction dir, boolean dummy) {
		super(g, f);
		this.direction = dir;
		this.dummy = dummy;
	}

	public void updateLogic() {
		if (dummy) {
			return;
		}
		int dx = 0;
		int dy = 0;
		int sx = 0;
		int sy = 0;
		Direction dir = this.direction;
		if (Keyboard.RIGHT.isDown()) {
			dx = 16;
			sx = 2;
			dir = Direction.Right;
		}
		else if (Keyboard.LEFT.isDown()) {
			dx = -16;
			sx = -2;
			dir = Direction.Left;
		}
		else if (Keyboard.UP.isDown()) {
			dy = -16;
			sy = -2;
			dir = Direction.Up;
		}
		else if (Keyboard.DOWN.isDown()) {
			dy = 16;
			sy = 2;
			dir = Direction.Down;
		}
		if (dx != 0 || dy != 0) {
			List<Entity> entlist = new ArrayList<Entity>();
			entlist.add(this);
			Field target = level.getFieldPix(getX() + dx, getY() + dy);
			if (target.accept(entlist, dir)) {
				// XXX: Process should set references!
				enqueueProcess(new MoveProcess(this, getX() + dx, getY() + dy, sx, sy));
				super.getField().unsetEntity();
				this.direction = dir;
				super.setField(target);
				target.setEntityHere(this);
			}
		}
	}

	public void renderImage(Screen screen) {
		int xt = 0;
		int yt = 0;
		
		switch (direction) {
		case Left: {
			xt = 6;
		} break;
		
		case Right: {
			xt = 2;
		} break;
		
		case Up: {
			xt = 0;
		} break;
		
		case Down: {
			xt = 4;
		} break;
		}
		
		screen.drawSprite(getX(),  getY(), xt, yt, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8,  getY(), xt + 1, yt, SpriteSheet.SHEET);
		screen.drawSprite(getX(),  getY() + 8, xt, yt + 1, SpriteSheet.SHEET);
		screen.drawSprite(getX() + 8,  getY() + 8, xt + 1, yt + 1, SpriteSheet.SHEET);
	}

	@Override
	public boolean push(Entity e, List<Entity> ents, Direction dir) {
		return e.pushBy(this, ents, dir);
		
	}

	@Override
	public boolean pushBy(Worker w, List<Entity> ents, Direction dir) {
		if (ents.size() == 1) {
			return false;
		}
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
	
	/*public boolean hitWall(Wall w, List<Entity> ents) {
		return w.hitBy(this, ents);
	}*/
	
	/*public boolean reachTarget(Target t, List<Entity> ents) {
		return t.reachBy(this, ents);
	}*/
	
	public void gainPoint() {
		points++;
	}
	
	public void losePoint() {
		points--;
	}
	
	public int getPoints() {
		return points;
	}

	public boolean visit(Field f, List<Entity> ents) {
		// TODO Auto-generated method stub
		return f.visitBy(this, ents);
	}
	
}
