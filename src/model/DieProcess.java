package model;

import gfx.Bitmap;
import gfx.Brush;
import gfx.Sprite;
import main.Game;

public class DieProcess implements Process {

	public static final float SPEED = 0.1f;
	
	private Worker worker;
	private Direction pushDirection;
	private Direction faceDirection;
	private float percent;
	private float speed;
	
	public DieProcess(Worker w, Direction pdir, Direction fdir) {
		this.worker = w;
		this.pushDirection = pdir;
		this.faceDirection = fdir;
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
		
		switch (pushDirection) {
		case Left: {
			sx = percent;
		} break;
		
		case Right: {
			sx = percent;
			xoff += (1.0f - percent) * Game.TILE_WIDTH;
		} break;
		
		case Up: {
			sy = percent;
		} break;
		
		case Down: {
			sy = percent;
			yoff += (1.0f - percent) * Game.TILE_HEIGHT;
		} break;
		}
		
		switch (faceDirection) {
		case Left: {
			spr = Sprite.PLAYER_LEFT0;
		} break;
		
		case Right: {
			spr = Sprite.PLAYER_RIGHT0;
		} break;
		
		case Up: {
			spr = Sprite.PLAYER_UP0;
		} break;
		
		case Down: {
			spr = Sprite.PLAYER_DOWN0;
		} break;
		}
		
		Brush b = bmp.getBrush();
		bmp.setBrush(worker.getBrush());
		spr.renderScaled(bmp, worker.getX() + xoff, worker.getY() + yoff, sx, sy);
		bmp.setBrush(b);
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
