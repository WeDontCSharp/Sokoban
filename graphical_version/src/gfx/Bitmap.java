package gfx;

public class Bitmap {
	private final int width;
	private final int height;
	private Brush brush;
	
	public final int[] pixels;
	
	public Bitmap(int w, int h, Brush brush) {
		this.width = w;
		this.height = h;
		this.pixels = new int[w * h];
		this.brush = brush;
	}
	
	protected Bitmap(int w, int h, int[] pixels, Brush brush) {
		this.width = w;
		this.height = h;
		this.pixels = pixels;
		this.brush = brush;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Brush getBrush() {
		return brush;
	}
	
	public void setBrush(Brush b) {
		this.brush = b;
	}
	
	public void clear(int col) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = col;
		}
	}
	
	public void blitScaled(int xp, int yp, int sx, int sy, int w, int h, float scalex, float scaley, Bitmap bmp) {
		float step_y = 1.0f / scaley;
		float step_x = 1.0f / scalex;
		int yo = 0;
		for (float y = 0.0f; y < h; y += step_y, yo++) {
			int yy = yo + yp;
			if (yy < 0) {
				yo += yy;
				y += step_y * yy;
				continue;
			}
			if (yy >= height) {
				break;
			}
			int by = (sy + (int)y) * bmp.getWidth();
			int yyop = yy * width;
			int xo = 0;
			for (float x = 0.0f; x < w; x += step_x, xo++) {
				int xx = xo + xp;
				if (xx < 0) {
					xo += xx;
					x += step_x * xx;
					continue;
				}
				if (xx >= width) {
					break;
				}
				pixels[yyop + xx] = brush.plot(pixels[yyop + xx], bmp.pixels[sx + (int)x + by]);
			}
		}
	}
	
	public void blit(int xp, int yp, int sx, int sy, int w, int h, Bitmap bmp) {
		for (int y = 0; y < h; y++) {
			int yy = yp + y;
			if (yy < 0) {
				y += yy;
				continue;
			}
			if (yy >= height) {
				break;
			}
			int yyp = yy * width;
			int by = (sy + y) * bmp.getWidth();
			for (int x = 0; x < w; x++) {
				int xx = x + xp;
				if (xx < 0) {
					x += xx;
					continue;
				}
				if (xx >= width) {
					break;
				}
				pixels[yyp + xx] = brush.plot(pixels[yyp + xx], bmp.pixels[by + sx + x]);
			}
		}
	}
}
