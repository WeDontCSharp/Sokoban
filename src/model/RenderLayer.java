package model;

import gfx.Bitmap;
import gfx.Brush;

public class RenderLayer implements IRenderable {
	private Bitmap bitmap;
	private Brush blitBrush;
	private int depth;

	public RenderLayer(int w, int h, Brush b, Brush blitBrush, int d) {
		this.bitmap = new Bitmap(w, h, b);
		this.blitBrush = blitBrush;
		this.depth = d;
	}
	
	public void renderShadow(Bitmap bmp) {
	}
	
	@Override
	public void render(Bitmap bmp, int xoff, int yoff) {
		Brush b = bmp.getBrush();
		bmp.setBrush(blitBrush);
		bmp.blit(xoff, yoff, 0, 0, bitmap.getWidth(), bitmap.getHeight(), bitmap);
		bmp.setBrush(b);
	}

	@Override
	public int getDepth() {
		return this.depth;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
}
