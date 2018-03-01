package model;

import java.util.Optional;

import gfx.Bitmap;
import gfx.Sprite;
import gfx.SpriteSheet;

public class Crate extends Entity {

	public Crate(Grid g, Field f) {
		super(g, f);
	}

	public void updateLogic() {
		
	}

	public void renderShadow(Bitmap bmp) {
		Sprite.BOX_SHADOW.render(getLevel().getShadowScreen(), getX(), getY());
	}
	
	@Override
	public void renderImage(Bitmap bmp, int xoff, int yoff) {
		//Sprite.BOX_SHADOW.render(getLevel().getShadowScreen(), getX() + xoff, getY() + yoff);
		
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 1, 3);
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() - 16 + yoff, 1, 2);
	}

	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = super.getField().getNeighbor(dir);
		if (nextField.canStepHere(firstPusher, this)){
			Optional<Entity> here = nextField.getEntityHere();
			if (!here.isPresent()) {
				enqueueProcess(new MoveProcess(this, nextField, Optional.empty()));
				super.getField().unsetEntity();
				super.setField(nextField);
				nextField.setEntityHere(firstPusher, this);
				return true;
			}
			else {
				Entity nextEntity = nextField.getEntityHere().get();
				if (push(firstPusher, nextEntity, dir)) {
					enqueueProcess(new MoveProcess(this, nextField, Optional.empty()));
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
	public void hitWall(Direction dir) {}

	@Override
	public void useSwitch() {
		System.err.println("Crate used switch.");
	}

	@Override
	public void reachTarget(Worker firstPusher) {
		System.err.println("Crate reached target.");
	}

	@Override
	public void hitSpawn() {
		// TODO Auto-generated method stub
		
	}

}
