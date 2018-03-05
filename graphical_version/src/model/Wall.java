package model;

import gfx.Bitmap;
import gfx.Sprite;
import gfx.SpriteSheet;

public class Wall extends Field  {

	public Wall(Grid level, int x, int y) {
		super(level, x, y);
	}

	public void update() {
		
	}

	public void renderShadow(Bitmap bmp) {
		Sprite.BOX_SHADOW.render(getLevel().getShadowScreen(), getX(), getY());
	}
	
	public void render(Bitmap bmp, int xoff, int yoff) {
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 0, 3);
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() - 16 + yoff, 0, 2);
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Worker w) {
		if (w != firstPusher) {
			w.hitWall(firstPusher.getDirection());
			return true;
		}
		return false;
	}

	@Override
	public boolean canStepHere(Worker firstPusher, Crate c) {
		return false;
	}

	@Override
	public int getDepth() {
		return -getY();
	}

}
