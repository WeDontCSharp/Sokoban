package model;

import java.util.List;
import java.util.Optional;

import gfx.Screen;
import gfx.SpriteSheet;
import io.Keyboard;
import main.Game;

public class Crate extends Entity {

	public Crate(Grid g, Field f) {
		super(g, f);
	}

	public void updateLogic() {
		
	}

	public void renderImage(Screen screen) {
		screen.drawSprite(getX(), getY(), 1, 3, SpriteSheet.SHEET);
		screen.drawSprite(getX(), getY() - 16, 1, 2, SpriteSheet.SHEET);
	}

	public boolean step(Worker firstPusher, Direction dir) {
		int dx = 0;
		int dy = 0;
		if (dir == Direction.Right) {
			dx = Game.TILE_WIDTH;
		}
		else if (dir == Direction.Left) {
			dx = -Game.TILE_WIDTH;
		}
		else if (dir == Direction.Up) {
			dy = -Game.TILE_HEIGHT;
		}
		else if (dir == Direction.Down) {
			dy = Game.TILE_HEIGHT;
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
		return step(firstPusher, dir);	
	}

	@Override
	public boolean pushBy(Worker firstPusher, Crate pusher, Direction dir) {
		return step(firstPusher, dir);
	}
	
	@Override
	public void fallDown(Worker firstPusher) {
		System.err.println("Crate fell.");
	}

	@Override
	public void hitWall() {}

	@Override
	public void useSwitch() {
		System.err.println("Crate used switch.");
	}

	@Override
	public void reachTarget(Worker firstPusher) {
		System.err.println("Crate reached target.");
	}

}
