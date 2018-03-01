package model;

import gfx.Bitmap;
import gfx.Brush;

public class ShadowLayer implements IRenderable {
	private Bitmap bitmap;

	public ShadowLayer(int w, int h, Brush b) {
		this.bitmap = new Bitmap(w, h, b);
	}
	
	public void renderShadow(Bitmap bmp) {
	}
	
	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		Brush b = bmp.getBrush();
		bmp.setBrush(Brush.SUBTRACT_BRUSH);
		bmp.blit(xoff, yoff, 0, 0, bitmap.getWidth(), bitmap.getHeight(), bitmap);
		bmp.setBrush(b);
	}

	@Override
	public int getDepth() {
		return 2;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
}
