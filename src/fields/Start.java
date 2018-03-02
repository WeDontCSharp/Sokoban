package fields;

import entities.Crate;
import entities.Worker;
import gfx.Bitmap;
import gfx.Brush;
import gfx.SpriteSheet;
import model.Grid;

public class Start extends Field {

	private Worker owner;
	
	public Start(Grid level, int x, int y) {
		super(level, x, y);
	}

	public void renderShadow(Bitmap bmp) {
	}

	public void update() {
	}

	public void render(Bitmap bmp, int xoff, int yoff) {
		Brush b = bmp.getBrush();
		bmp.setBrush(owner.getBrush());
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 9, 4);
		bmp.setBrush(b);
	}

	public boolean canStepHere(Worker firstPusher, Worker w) {
		return w == owner;
	}

	public boolean canStepHere(Worker firstPusher, Crate c) {
		return false;
	}

	public int getDepth() {
		return 10000;
	}
	
	public void setWorker(Worker w) {
		this.owner = w;
	}

}
