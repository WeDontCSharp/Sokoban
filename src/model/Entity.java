package model;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import gfx.Screen;

public abstract class Entity {
	protected Grid level;
	private Queue<Process> processes;
	private int x;
	private int y;
	private Field field;
	
	public Entity(Grid level, Field f) {
		this.level = level;
		this.processes = new LinkedBlockingQueue<Process>();
		this.x = f.getX();
		this.y = f.getY();
		this.field = f;
		f.setEntityHere(this);
	}
	
	public void update() {
		if (!processes.isEmpty()) {
			Process proc = processes.element();
			if (proc.isDone()) {
				processes.remove();
			}
			else {
				proc.update();
				return;
			}
		}
		updateLogic();
	}
	
	public void render(Screen screen) {
		// DEBUG //////////
		screen.drawLine(x + 8, y + 8, field.getX() + 8, field.getY() + 8, 0xffff0000);
		Optional<Entity> etof = field.getEntityHere();
		if (etof.isPresent()) {
			Entity ee = etof.get();
			screen.drawLine(field.getX() + 9, field.getY() + 9, ee.getX() + 9, ee.getY() + 9, 0xff00ff00);
		}
		else {
			screen.drawRect(field.getX() + 4, field.getY() + 4, 8, 8, 0xff00ff00);
		}
		//////////////////
		if (!processes.isEmpty()) {
			processes.element().render(screen);
			return;
		}
		renderImage(screen);	
	}
	
	public abstract void updateLogic();
	public abstract void renderImage(Screen screen);
	
	public abstract boolean push(Worker firstPusher, Entity pushed, Direction dir);
	public abstract boolean pushBy(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushBy(Worker firstPusher, Crate pusher, Direction dir);
	
	public abstract void fallDown(Worker firstPusher);
	public abstract void hitWall();
	public abstract void useSwitch();
	public abstract void reachTarget(Worker firstPusher);
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setField(Field f) {
		this.field = f;
	}
	
	public Field getField() {
		return this.field;
	}
	
	public void enqueueProcess(Process proc) {
		this.processes.add(proc);
	}
}
