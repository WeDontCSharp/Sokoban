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
	private int points;
	private int health;
	
	private PlayerControls controls;
	
	public Worker(Grid g, Field f, Direction dir, PlayerControls ctrl) {
		super(g, f);
		
		this.playerLeft = new Animation(new Sprite[] { Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT1, Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT2 }, 5);
		this.playerRight = new Animation(new Sprite[] { Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT1, Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT2 }, 5);
		this.playerUp = new Animation(new Sprite[] { Sprite.PLAYER_UP0, Sprite.PLAYER_UP1, Sprite.PLAYER_UP0, Sprite.PLAYER_UP2 }, 5);
		this.playerDown = new Animation(new Sprite[] { Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN1, Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN2 }, 5);
		
		this.currentAnim = this.playerRight;
		
		this.direction = dir;
		this.controls = ctrl;
	}

	public void updateLogic() {
		Optional<Direction> moveDir = controls.getControl();
		if (moveDir.isPresent()) {
			this.direction = moveDir.get();
			step(this, this.direction);
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
		enqueueProcess(new DieProcess(this, dir, this.direction));
		System.err.println("Worker hit wall.");
	}

	@Override
	public void useSwitch() {}

	@Override
	public void reachTarget(Worker firstPusher) {}

	@Override
	public void hitSpawn() {
		// TODO Auto-generated method stub
		
	}
	
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
