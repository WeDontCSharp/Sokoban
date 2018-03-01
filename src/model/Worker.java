package model;

import java.util.Optional;

import gfx.Animation;
import gfx.Bitmap;
import gfx.Sprite;

public class Worker extends Entity {

	private Animation playerLeft;
	private Animation playerRight;
	private Animation playerUp;
	private Animation playerDown;
	
	private Animation playerPushLeft;
	private Animation playerPushRight;
	private Animation playerPushUp;
	private Animation playerPushDown;
	
	private Animation currentAnim;
	
	private Direction direction;
	private int points;
	private int health;
	private Start start;
	
	private PlayerControls controls;
	
	public Worker(Grid g, Field f, Direction dir, PlayerControls ctrl, Start st) {
		super(g, f);
		
		this.playerLeft = new Animation(new Sprite[] { Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT1, Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT2 }, 5);
		this.playerRight = new Animation(new Sprite[] { Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT1, Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT2 }, 5);
		this.playerUp = new Animation(new Sprite[] { Sprite.PLAYER_UP0, Sprite.PLAYER_UP1, Sprite.PLAYER_UP0, Sprite.PLAYER_UP2 }, 5);
		this.playerDown = new Animation(new Sprite[] { Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN1, Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN2 }, 5);
		
		this.playerPushLeft = new Animation(new Sprite[] { Sprite.PLAYER_PLEFT0, Sprite.PLAYER_PLEFT1, Sprite.PLAYER_PLEFT0, Sprite.PLAYER_PLEFT2 }, 5);
		this.playerPushRight = new Animation(new Sprite[] { Sprite.PLAYER_PRIGHT0, Sprite.PLAYER_PRIGHT1, Sprite.PLAYER_PRIGHT0, Sprite.PLAYER_PRIGHT2 }, 5);
		this.playerPushUp = new Animation(new Sprite[] { Sprite.PLAYER_PUP0, Sprite.PLAYER_PUP1, Sprite.PLAYER_PUP0, Sprite.PLAYER_PUP2 }, 5);
		this.playerPushDown = new Animation(new Sprite[] { Sprite.PLAYER_PDOWN0, Sprite.PLAYER_PDOWN1, Sprite.PLAYER_PDOWN0, Sprite.PLAYER_PDOWN2 }, 5);
		
		this.currentAnim = this.playerRight;
		
		this.direction = dir;
		this.controls = ctrl;
		this.start = st;
	}

	public void updateLogic() {
		Optional<Direction> moveDir = controls.getControl();
		if (moveDir.isPresent()) {
			this.direction = moveDir.get();
			switch (this.direction) {
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
			step(this, this.direction);
		}
		else {
			switch (this.direction) {
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
			this.currentAnim.reset();
		}
	}

	public void renderShadow(Bitmap bmp) {
		Sprite.PLAYER_SHADOW.render(getLevel().getShadowScreen(), getX(), getY());
	}
	
	@Override
	public void renderImage(Bitmap bmp, int xoff, int yoff) {
		currentAnim.render(bmp, getX() + xoff, getY() + yoff);
	}
	
	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = super.getField().getNeighbor(dir);
		if (nextField.canStepHere(firstPusher, this)){
			Optional<Entity> here = nextField.getEntityHere();
			if (!here.isPresent()) {
				if (this == firstPusher) {
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
						Animation anim = null;
						switch (this.direction) {
						case Up:
							anim = playerPushUp;
							break;
						case Down:
							anim = playerPushDown;
							break;
						case Left:
							anim = playerPushLeft;
							break;
						case Right:
							anim = playerPushRight;
							break;
						}
						currentAnim = anim;
						enqueueProcess(new MoveProcess(this, nextField, Optional.of(anim)));
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
		Field f = start;
		this.setPos(f.getX(), f.getY());
		this.setField(f);
		f.setEntityHere(this);
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
}
