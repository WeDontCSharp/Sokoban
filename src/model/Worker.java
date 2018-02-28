package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;
import io.Keyboard;

public class Worker extends Entity {

	private Direction direction;
	private boolean dummy;
	private int points;
	private int health;
	
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
		Direction dir = this.direction;
		if (Keyboard.RIGHT.isDown()) {
			dx = 16;
			dir = Direction.Right;
		}
		else if (Keyboard.LEFT.isDown()) {
			dx = -16;
			dir = Direction.Left;
		}
		else if (Keyboard.UP.isDown()) {
			dy = -16;
			dir = Direction.Up;
		}
		else if (Keyboard.DOWN.isDown()) {
			dy = 16;
			dir = Direction.Down;
		}
		this.direction = dir;
		if (dx != 0 || dy != 0) {
			step(this, dir);
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
	
	public boolean step(Worker firstPusher, Direction dir) {
		int dx = 0;
		int dy = 0;
		if (dir == Direction.Right) {
			dx = 16;
		}
		else if (dir == Direction.Left) {
			dx = -16;
		}
		else if (dir == Direction.Up) {
			dy = -16;
		}
		else if (dir == Direction.Down) {
			dy = 16;
		}
		Field nextField = level.getFieldPix(getX() + dx, getY() + dy);
		if (nextField.canStepHere(firstPusher, this)){
			Optional<Entity> here = nextField.getEntityHere();
			if (!here.isPresent()) {
				enqueueProcess(new MoveProcess(this, nextField));
				super.getField().unsetEntity();
				super.setField(nextField);
				nextField.setEntityHere(firstPusher, this);
				return true;
			}
			else {
				Entity nextEntity = nextField.getEntityHere().get();
				if (push(firstPusher, nextEntity, dir)) {
					enqueueProcess(new MoveProcess(this, nextField));
					super.getField().unsetEntity();
					super.setField(nextField);
					nextField.setEntityHere(firstPusher, this);
					return true;
				}
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		return pushed.pushBy(firstPusher, this, dir);
	}

	@Override
	public boolean pushBy(Worker firstPusher, Worker pushed, Direction dir) {
		if (firstPusher == pushed) {
			return false;
		}
		return step(firstPusher, dir);	
	}

	@Override
	public boolean pushBy(Worker firstPusher, Crate pusher, Direction dir) {
		return step(firstPusher, dir);
	}
	
	public void gainPoint() {
		points++;
		System.err.println("Worker gained point.");
	}
	
	public void losePoint() {
		points--;
		System.err.println("Worker lost point.");
	}
	
	public int getPoints() {
		return points;
	}
	
	public void gainHealth() {
		health++;
		System.err.println("Worker gained health.");
	}
	
	public void loseHealth() {
		health--;
		System.err.println("Worker lost health.");
	}
	
	public int getHealth() {
		return health;
	}

	@Override
	public void fallDown(Worker firstPusher) {
		System.err.println("Worker fell.");
	}

	@Override
	public void hitWall() {
		System.err.println("Worker hit wall.");
	}

	@Override
	public void useSwitch() {}

	@Override
	public void reachTarget(Worker firstPusher) {}
	
}
