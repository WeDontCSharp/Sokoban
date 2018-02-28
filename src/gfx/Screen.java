package gfx;

public class Screen {
	public final int width;
	public final int height;
	public int[] pixels;

	public Screen(int w, int h) {
		this.width = w;
		this.height = h;
		this.pixels = new int[w * h];
	}

	public void drawSprite(int x, int y, int xt, int yt, SpriteSheet sheet) {
		for (int j = 0; j < sheet.tileSize; j++) {
			int yyy = y + j;
			if (yyy < 0 || yyy >= height) {
				continue;
			}
			int yp = (yyy) * width;
			int typ = (yt * sheet.tileSize + j) * sheet.width;
			for (int i = 0; i < sheet.tileSize; i++) {
				int xp = x + i;
				if (xp < 0 || xp >= width) {
					continue;
				}
				int col = sheet.pixels[typ + xt * sheet.tileSize + i];
				if (col == 0xffff00ff || col == 0xff800080) {
					continue;
				}
				pixels[xp + yp] = col;
			}
		}
	}

	public void drawLine(int x0, int y0, int x1, int y1, int col) {
		int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
		int dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
		int err = (dx > dy ? dx : -dy) / 2, e2;

		for (;;) {
			pixels[x0 + y0 * width] = col;
			if (x0 == x1 && y0 == y1)
				break;
			e2 = err;
			if (e2 > -dx) {
				err -= dy;
				x0 += sx;
			}
			if (e2 < dy) {
				err += dx;
				y0 += sy;
			}
		}
	}

	public void drawGrid(int delta, int col) {
		for (int i = 1; i < width / delta; i++) {
			drawLine(i * delta, 0, i * delta, height - 1, col);
		}
		for (int i = 1; i < height / delta; i++) {
			drawLine(0, i * delta, width - 1, i * delta, col);
		}
	}
	
	public void drawRect(int x, int y, int w, int h, int col) {
		for (int j = 0; j < h; j++) {
			int yp = y * width;
			for (int i = 0; i < w; i++) {
				pixels[yp + i] = col;
			}
		}
	}
}
