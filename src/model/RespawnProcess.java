package model;

import entities.Worker;
import gfx.Bitmap;
import gfx.Brush;

public class RespawnProcess implements Process {

	private static final int TICK_COUNT = 120;
	private static final int TICK_FLICK = 15;
	
	private Worker worker;
	private int tickCount;
	private int tickGoal;
	private int tickFlick;
	private Brush brush;
	
	public RespawnProcess(Worker w) {
		this.worker = w;
		this.tickCount = 0;
		this.tickGoal = TICK_COUNT;
		this.tickFlick = TICK_FLICK;
		this.brush = new Brush.CombineBrush(Brush.BLEND_BRUSH, w.getBrush());
	}
	
	@Override
	public void update() {
		this.tickCount++;
	}

	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		if (this.tickCount % (this.tickFlick * 2) > this.tickFlick) {
			// No render
		}
		else {
			Brush b = this.worker.getBrush();
			this.worker.setBrush(this.brush);
			this.worker.renderImage(bmp, xoff, yoff);
			this.worker.setBrush(b);
		}
	}

	@Override
	public boolean isDone() {
		return this.tickCount >= this.tickGoal;
	}

	@Override
	public void terminate() {
	}

}
