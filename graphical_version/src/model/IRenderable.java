package model;

import gfx.Bitmap;

public interface IRenderable {
	public void renderShadow(Bitmap bmp);
	public void render(Bitmap bmp, int xoff, int yoff);
	public int getDepth();
}
