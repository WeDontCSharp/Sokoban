package entities;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import fields.Field;
import gfx.Bitmap;
import model.Direction;
import model.Grid;
import model.IRenderable;
import model.Process;

public abstract class Entity implements IRenderable {
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
				proc.terminate();
			}
			else {
				proc.update();
				return;
			}
		}
		updateLogic();
	}
	
	private void debugDrawFieldRelation(Bitmap bmp, int xoff, int yoff) {
		// XXX
		/*
		bmp.drawLine(x + 8 + xoff, y + 4 + yoff, field.getX() + 8 + xoff, field.getY() + 4 + yoff, 0xffff0000);
		Optional<Entity> etof = field.getEntityHere();
		if (etof.isPresent()) {
			Entity ee = etof.get();
			bmp.drawLine(field.getX() + 9 + xoff, field.getY() + 5 + yoff, ee.getX() + 5 + xoff, ee.getY() + 5 + yoff, 0xff00ff00);
		}
		else {
			bmp.drawRect(field.getX() + 4 + xoff, field.getY() + 4 + yoff, 8, 8, 0xff00ff00);
		}
		*/
	}
	
	public void render(Bitmap bmp, int xoff, int yoff) {
		debugDrawFieldRelation(bmp, xoff, yoff);
		
		if (!processes.isEmpty()) {
			processes.element().render(bmp, xoff, yoff);
			return;
		}
		renderImage(bmp, xoff, yoff);
	}
	
	public abstract void updateLogic();
	public abstract void renderImage(Bitmap bmp, int xoff, int yoff);
	
	public abstract boolean push(Worker firstPusher, Entity pushed, Direction dir);
	public abstract boolean pushBy(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushBy(Worker firstPusher, Crate pusher, Direction dir);
	
	public abstract void fallDown(Worker firstPusher);
	public abstract void hitSpawn();
	public abstract void hitWall(Direction dir);
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
	
	public int getDepth() {
		return -y;
	}
	
	public Grid getLevel() {
		return level;
	}
	
	public void setLevel(Grid lvl) {
		this.level = lvl;
	}
	
	public void abortAllProcesses() {
		processes.clear();
	}
}
