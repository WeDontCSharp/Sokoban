package model;

import java.util.Optional;

import gfx.Animation;
import gfx.Bitmap;
import gfx.Sprite;
import io.Keyboard;

public class Worker extends Entity {

	private Animation playerLeft;
	private Animation playerRight;
	private Animation playerUp;
	private Animation playerDown;
	
	private Animation currentAnim;
	
	private Direction direction;
	private boolean dummy;
	private int points;
	private int health;
	
	public Worker(Grid g, Field f, Direction dir, boolean dummy) {
		super(g, f);
		
		this.playerLeft = new Animation(new Sprite[] { Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT1, Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT2 }, 5);
		this.playerRight = new Animation(new Sprite[] { Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT1, Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT2 }, 5);
		this.playerUp = new Animation(new Sprite[] { Sprite.PLAYER_UP0, Sprite.PLAYER_UP1, Sprite.PLAYER_UP0, Sprite.PLAYER_UP2 }, 5);
		this.playerDown = new Animation(new Sprite[] { Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN1, Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN2 }, 5);
		
		this.currentAnim = this.playerRight;
		
		this.direction = dir;
		this.dummy = dummy;
	}

	public void updateLogic() {
		if (dummy) {
			return;
		}
		boolean moved = false;
		Direction dir = this.direction;
		if (Keyboard.RIGHT.isDown()) {
			moved = true;
			dir = Direction.Right;
		}
		else if (Keyboard.LEFT.isDown()) {
			moved = true;
			dir = Direction.Left;
		}
		else if (Keyboard.UP.isDown()) {
			moved = true;
			dir = Direction.Up;
		}
		else if (Keyboard.DOWN.isDown()) {
			moved = true;
			dir = Direction.Down;
		}
		if (moved) {
			this.direction = dir;
			step(this, dir);
		}
		else {
			this.currentAnim.reset();
		}
	}

	public void renderShadow(Bitmap bmp) {
		Sprite.PLAYER_SHADOW.render(getLevel().getShadowScreen(), getX(), getY());
	}
	
	@Override
	public void renderImage(Bitmap bmp, int xoff, int yoff) {
		switch (direction) {
		case Left: {
			this.currentAnim = this.playerLeft;
		} break;
		
		case Right: {
			this.currentAnim = this.playerRight;
		} break;
		
		case Up: {
			this.currentAnim = this.playerUp;
		} break;
		
		case Down: {
			this.currentAnim = this.playerDown;
		} break;
		}
		
		currentAnim.render(bmp, getX() + xoff, getY() + yoff);
	}
	
	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = super.getField().getNeighbor(dir);
		if (nextField.canStepHere(firstPusher, this)){
			Optional<Entity> here = nextField.getEntityHere();
			if (!here.isPresent()) {
				if (this == firstPusher) {
					// XXX: Pushing animations
					enqueueProcess(new MoveProcess(this, nextField, Optional.of(currentAnim)));
				}
				else {
					enqueueProcess(new MoveProcess(this, nextField, Optional.empty()));
				}
				super.getField().unsetEntity();
				super.setField(nextField);
				nextField.setEntityHere(firstPusher, this);
				return true;
			}
			else {
				Entity nextEntity = nextField.getEntityHere().get();
				if (push(firstPusher, nextEntity, dir)) {
					if (this == firstPusher) {
						// XXX: Pushing animations
						enqueueProcess(new MoveProcess(this, nextField, Optional.of(currentAnim)));
					}
					else {
						enqueueProcess(new MoveProcess(this, nextField, Optional.empty()));
					}
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
	public void hitWall(Direction dir) {
		enqueueProcess(new DieProcess(this, dir));
		System.err.println("Worker hit wall.");
	}

	@Override
	public void useSwitch() {}

	@Override
	public void reachTarget(Worker firstPusher) {}
	
	public void resetPosition() {
		Field f = getLevel().getField(1, 1);
		this.setPos(f.getX(), f.getY());
		this.setField(f);
		f.setEntityHere(this);
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
}
