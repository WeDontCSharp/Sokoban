package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	public static SpriteSheet SHEET;
	
	static {
		 try {
			SHEET = new SpriteSheet("src/tileset2.png", 16);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final int width;
	public final int height;
	public final int tileSize;
	public final int[] pixels;
	
	public SpriteSheet(String src, int ts) throws IOException {
		BufferedImage image = ImageIO.read(new File(src));
		
		tileSize = ts;
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}
}
