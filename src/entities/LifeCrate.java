package entities;

import fields.Field;
import gfx.Bitmap;
import gfx.SpriteSheet;
import model.Grid;

public class LifeCrate extends Crate {

	public LifeCrate(Grid g, Field f) {
		super(g, f);
	}

	public void updateLogic() {
		
	}

	@Override
	public void renderImage(Bitmap bmp, int xoff, int yoff) {
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() + yoff, 2, 3);
		SpriteSheet.SHEET.blitSpriteTo(bmp, getX() + xoff, getY() - 16 + yoff, 2, 2);
	}
	
	@Override
	public void fallDown(Worker firstPusher) {
		System.err.println("LifeCrate fell.");
		firstPusher.gainHealth();
	}

}
