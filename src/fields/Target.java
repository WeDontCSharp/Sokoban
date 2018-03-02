package fields;

import entities.Crate;
import entities.Worker;
import gfx.Bitmap;
import gfx.SpriteSheet;
import model.Grid;

public class Target extends Floor {
	
	private Worker whoPushed;

	public Target(Grid level, int x, int y) {
		super(level, x, y);
	}

	public void update() {
		
	}

	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 1, 4);
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
			System.err.println("Crate removed from target.");
		}
		
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Worker w) {
		super.setEntityHere(w);
		w.reachTarget(firstPusher);
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Crate c) {
		super.setEntityHere(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		c.reachTarget(firstPusher);
	}

}
