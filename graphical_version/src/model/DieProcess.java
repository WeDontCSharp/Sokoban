package model;

import gfx.Bitmap;
import gfx.Sprite;
import main.Game;

public class DieProcess implements Process {

	public static final float SPEED = 0.1f;
	
	private Worker worker;
	private Direction direction;
	private float percent;
	private float speed;
	
	public DieProcess(Worker w, Direction dir) {
		this.worker = w;
		this.direction = dir;
		this.percent = 1.0f;
		this.speed = SPEED;
	}
	
	@Override
	public void update() {
		if (percent <= 0.0f) {
			return;
		}
		
		percent -= speed;
		if (percent < 0.0f) {
			percent = 0.0f;
		}
	}

	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		Sprite spr = null;
		float sx = 1.0f;
		float sy = 1.0f;
		
		switch (direction) {
		case Left: {
			spr = Sprite.PLAYER_LEFT0;
			sx = percent;
		} break;
		
		case Right: {
			spr = Sprite.PLAYER_RIGHT0;
			sx = percent;
			xoff += (1.0f - percent) * Game.TILE_WIDTH;
		} break;
		
		case Up: {
			spr = Sprite.PLAYER_UP0;
			sy = percent;
		} break;
		
		case Down: {
			spr = Sprite.PLAYER_DOWN0;
			sy = percent;
			yoff += (1.0f - percent) * Game.TILE_HEIGHT;
		} break;
		}
		
		spr.renderScaled(bmp, worker.getX() + xoff, worker.getY() + yoff, sx, sy);
	}

	@Override
	public boolean isDone() {
		return percent <= 0.0f;
	}

	@Override
	public void terminate() {
		worker.resetPosition();
		worker.abortAllProcesses();
	}
	
}
