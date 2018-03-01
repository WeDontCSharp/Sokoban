package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet extends Bitmap {
	
	public static final SpriteSheet SHEET = fromFile("src/tileset2.png", 16);
	
	private final int tileSize;
	
	public static SpriteSheet fromFile(String path, int ts) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			int w = image.getWidth();
			int h = image.getHeight();
			int[] pix = image.getRGB(0, 0, w, h, null, 0, w);
			return new SpriteSheet(w, h, pix, ts);
		}
		catch (IOException ex) {
			return null;
		}
	}
	
	private SpriteSheet(int w, int h, int[] pixels, int tileSiz) {
		super(w, h, pixels, Brush.SIMPLE_BRUSH);
		this.tileSize = tileSiz;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public void blitSpriteTo(Bitmap targ, int x, int y, int xt, int yt) {
		targ.blit(x, y, xt * tileSize, yt * tileSize, tileSize, tileSize, this);
	}
	
	public void blitSpriteToScaled(Bitmap targ, int x, int y, int xt, int yt, float sx, float sy) {
		targ.blitScaled(x, y, xt * tileSize, yt * tileSize, tileSize, tileSize, sx, sy, this);
	}
}
