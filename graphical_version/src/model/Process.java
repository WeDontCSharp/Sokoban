package model;

import gfx.Bitmap;

public interface Process {
	public void update();
	public void render(Bitmap bmp, int xoff, int yoff);
	public boolean isDone();
	public void terminate();
}
