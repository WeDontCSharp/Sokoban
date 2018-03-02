package entities;

import java.util.Optional;

import fields.Field;
import fields.Start;
import gfx.Animation;
import gfx.Bitmap;
import gfx.Brush;
import gfx.Sprite;
import model.DieProcess;
import model.Direction;
import model.Grid;
import model.MoveProcess;
import model.PlayerControls;

public class Worker extends Entity {

	private Animation[] playerAnims;
	private Animation[] playerPushAnims;
	
	private Animation currentAnim;
	
	private Direction direction;
	private int points;
	private int health;
	private Start start;
	
	private PlayerControls controls;
	private Brush brush;
	
	public Worker(Grid g, Field f, Direction dir, PlayerControls ctrl, Start st, Brush br) {
		super(g, f);
		
		this.playerAnims = new Animation[Direction.values().length];
		this.playerPushAnims = new Animation[Direction.values().length]; 
		
		this.playerAnims[Direction.Left.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT1, Sprite.PLAYER_LEFT0, Sprite.PLAYER_LEFT2 }, 5);
		this.playerAnims[Direction.Right.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT1, Sprite.PLAYER_RIGHT0, Sprite.PLAYER_RIGHT2 }, 5);
		this.playerAnims[Direction.Up.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_UP0, Sprite.PLAYER_UP1, Sprite.PLAYER_UP0, Sprite.PLAYER_UP2 }, 5);
		this.playerAnims[Direction.Down.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN1, Sprite.PLAYER_DOWN0, Sprite.PLAYER_DOWN2 }, 5);
		
		this.playerPushAnims[Direction.Left.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_PLEFT0, Sprite.PLAYER_PLEFT1, Sprite.PLAYER_PLEFT0, Sprite.PLAYER_PLEFT2 }, 5);
		this.playerPushAnims[Direction.Right.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_PRIGHT0, Sprite.PLAYER_PRIGHT1, Sprite.PLAYER_PRIGHT0, Sprite.PLAYER_PRIGHT2 }, 5);
		this.playerPushAnims[Direction.Up.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_PUP0, Sprite.PLAYER_PUP1, Sprite.PLAYER_PUP0, Sprite.PLAYER_PUP2 }, 5);
		this.playerPushAnims[Direction.Down.ordinal()] = new Animation(new Sprite[] { Sprite.PLAYER_PDOWN0, Sprite.PLAYER_PDOWN1, Sprite.PLAYER_PDOWN0, Sprite.PLAYER_PDOWN2 }, 5);
		
		this.currentAnim = playerAnims[dir.ordinal()];
		
		this.direction = dir;
		this.controls = ctrl;
		this.start = st;
		this.brush = br;
	}
	
	public void updateLogic() {
		Optional<Direction> moveDir = controls.getControl();
		if (moveDir.isPresent()) {
			this.direction = moveDir.get();
			this.currentAnim = playerAnims[direction.ordinal()];
			step(this, this.direction);
		}
		else {
			this.currentAnim = playerAnims[direction.ordinal()];
			this.currentAnim.reset();
		}
	}

	public void renderShadow(Bitmap bmp) {
		Sprite.PLAYER_SHADOW.render(getLevel().getShadowScreen(), getX(), getY());
	}
	
	@Override
	public void renderImage(Bitmap bmp, int xoff, int yoff) {
		Brush b = bmp.getBrush();
		bmp.setBrush(this.brush);
		this.currentAnim.render(bmp, getX() + xoff, getY() + yoff);
		bmp.setBrush(b);
	}
	
	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = super.getField().getNeighbor(dir);
		if (nextField.canStepHere(firstPusher, this)){
			Optional<Entity> here = nextField.getEntityHere();
			if (!here.isPresent()) {
				if (this == firstPusher) {
					enqueueProcess(new MoveProcess(this, nextField, Optional.of(this.currentAnim)));
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
						this.currentAnim = playerPushAnims[direction.ordinal()];
						enqueueProcess(new MoveProcess(this, nextField, Optional.of(this.currentAnim)));
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
	
	public Brush getBrush() {
		return this.brush;
	}
	
}
