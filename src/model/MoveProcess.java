package model;

import gfx.Screen;

public class MoveProcess implements Process {

	public static final float SPEED = 0.1f;
	
	private Entity entity;
	private Field sourceField;
	private Field targetField;
	private float percentage;
	private float speed;
	
	public MoveProcess(Entity e, Field targ) {
		this.entity = e;
		this.sourceField = e.getField();
		this.targetField = targ;
		this.percentage = 0;
		this.speed = SPEED;
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
	}

	public void render(Screen screen) {
		entity.renderImage(screen);
	}

	public boolean isDone() {
		return percentage >= 1;
	}
	
}
