package fields;

import entities.Crate;
import entities.Worker;
import gfx.Bitmap;
import gfx.SpriteSheet;
import model.Grid;

public class Floor extends Field {

	public Floor(Grid level, int x, int y) {
		super(level, x, y);
	}

	public void update() {
		
	}

	public void renderShadow(Bitmap bmp) {
	}
	
	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 0, 4);
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Worker w) {
		return true;
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Crate c) {
		return true;
	}

	@Override
	public int getDepth() {
		return 10000;
	}

}
