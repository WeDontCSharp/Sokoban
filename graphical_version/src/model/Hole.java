package model;

import gfx.Bitmap;
import gfx.SpriteSheet;

public class Hole extends Floor {
	
	private boolean open = false;
	private Worker whoPushed;
	
	public Hole(Grid level, int x, int y) {
		super(level, x, y);
	}

	public void update() {
		
	}

	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		if (isOpen()) {
			SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 4, 4);
		}else {
			SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 3, 4);
		}
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Worker w) {
		super.setEntityHere(w);
		if (open) {
			w.fallDown(firstPusher);
		}
	}
	
	@Override
	public void setEntityHere(Worker firstPusher, Crate c) {
		super.setEntityHere(c);
		whoPushed = firstPusher;
		if (open) {
			c.fallDown(firstPusher);
		}
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public Worker getWhoPushed() {
		return whoPushed;
	}

}
