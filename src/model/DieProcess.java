package model;

import entities.Worker;
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
	private boolean triggeredBlood;
	
	public DieProcess(Worker w, Direction pdir, Direction fdir) {
		this.worker = w;
		this.pushDirection = pdir;
		this.faceDirection = fdir;
		this.percent = 1.0f;
		this.speed = SPEED;
		this.triggeredBlood = false;
	}
	
	@Override
	public void update() {
		if (percent <= 0.0f) {
			return;
		}
		
		percent -= speed;
		
		if (!this.triggeredBlood && percent <= 0.3f) {
			this.triggeredBlood = true;
			
			int xoff = 0;
			int yoff = 0;
			float mindir = 0.0f;
			switch (this.pushDirection) {
			case Left: 
				mindir = -1.57f;
				break;
			case Right:
				mindir = 1.57f;
				xoff += Game.TILE_WIDTH;
				break;
			case Up:
				mindir = 0.0f;
				break;
			case Down:
				mindir = 3.14f;
				yoff += Game.TILE_HEIGHT;
				break;
			}
			
			worker.getLevel().getBloodLayer().add(
					new BloodSplatter(worker.getX() + xoff, worker.getY() + yoff, 50, mindir, mindir + 3.14f));
		}
		
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
