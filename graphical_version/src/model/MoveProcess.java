package model;

import java.util.Optional;

import gfx.Animation;
import gfx.Bitmap;

public class MoveProcess implements Process {

	public static final float SPEED = 0.1f;
	
	private Entity entity;
	private Field sourceField;
	private Field targetField;
	private float percentage;
	private float speed;
	private Optional<Animation> animation;
	
	public MoveProcess(Entity e, Field targ, Optional<Animation> anim) {
		this.entity = e;
		this.sourceField = e.getField();
		this.targetField = targ;
		this.percentage = 0;
		this.speed = SPEED;
		this.animation = anim;
	}

	public void update() {
		if (percentage >= 1) {
			return;
		}

		percentage += speed;
		if (percentage > 1) {
			percentage = 1;
		}
		// LERP
		int dx = targetField.getX() - sourceField.getX();
		int dy = targetField.getY() - sourceField.getY();
		
		entity.setX(sourceField.getX() + (int)(dx * percentage));
		entity.setY(sourceField.getY() + (int)(dy * percentage));
		
		if (this.animation.isPresent()) {
			this.animation.get().update();
		}
	}

	public void render(Bitmap bmp, int xoff, int yoff) {
		entity.renderImage(bmp, xoff, yoff);
	}

	public boolean isDone() {
		return percentage >= 1;
	}
	
	public void terminate() {
		if (this.animation.isPresent()) {
			//this.animation.get().reset();
		} 
	}
	
}
