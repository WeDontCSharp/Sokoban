package model;

import gfx.Screen;

public class MoveProcess implements Process {

	private Entity entity;
	private int goalx;
	private int goaly;
	private int xspeed;
	private int yspeed;
	
	public MoveProcess(Entity e, int goalx, int goaly, int xsp, int ysp) {
		this.entity = e;
		this.goalx = goalx;
		this.goaly = goaly;
		this.xspeed = xsp;
		this.yspeed = ysp;
	}

	public void update() {
		entity.setX(entity.getX() + xspeed);
		entity.setY(entity.getY() + yspeed);
	}

	public void render(Screen screen) {
		entity.renderImage(screen);
	}

	public boolean isDone() {
		return entity.getX() == goalx && entity.getY() == goaly;
	}
	
}
