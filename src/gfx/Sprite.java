package gfx;

public class Sprite {
	// Simple movement
	public static final Sprite PLAYER_DOWN0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(1, 1, 0, 0), new SpriteFrame(1, 0, 0, -16) });
	public static final Sprite PLAYER_DOWN1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(4, 1, 0, 0), new SpriteFrame(4, 0, 0, -16) });
	public static final Sprite PLAYER_DOWN2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(5, 1, 0, 0), new SpriteFrame(5, 0, 0, -16) });
	
	public static final Sprite PLAYER_UP0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(0, 1, 0, 0), new SpriteFrame(0, 0, 0, -16) });
	public static final Sprite PLAYER_UP1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(6, 1, 0, 0), new SpriteFrame(6, 0, 0, -16) });
	public static final Sprite PLAYER_UP2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(7, 1, 0, 0), new SpriteFrame(7, 0, 0, -16) });
	
	public static final Sprite PLAYER_LEFT0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(2, 1, 0, 0), new SpriteFrame(2, 0, 0, -16) });
	public static final Sprite PLAYER_LEFT1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(10, 1, 0, 0), new SpriteFrame(10, 0, 0, -16) });
	public static final Sprite PLAYER_LEFT2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(11, 1, 0, 0), new SpriteFrame(11, 0, 0, -16) });
	
	public static final Sprite PLAYER_RIGHT0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(3, 1, 0, 0), new SpriteFrame(3, 0, 0, -16) });
	public static final Sprite PLAYER_RIGHT1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(8, 1, 0, 0), new SpriteFrame(8, 0, 0, -16) });
	public static final Sprite PLAYER_RIGHT2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(9, 1, 0, 0), new SpriteFrame(9, 0, 0, -16) });
	
	// Push movement
	public static final Sprite PLAYER_PDOWN0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(1, 6, 0, 0), new SpriteFrame(1, 5, 0, -16) });
	public static final Sprite PLAYER_PDOWN1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(4, 6, 0, 0), new SpriteFrame(4, 5, 0, -16) });
	public static final Sprite PLAYER_PDOWN2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(5, 6, 0, 0), new SpriteFrame(5, 5, 0, -16) });
	
	public static final Sprite PLAYER_PUP0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(0, 6, 0, 0), new SpriteFrame(0, 5, 0, -16) });
	public static final Sprite PLAYER_PUP1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(6, 6, 0, 0), new SpriteFrame(6, 5, 0, -16) });
	public static final Sprite PLAYER_PUP2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(7, 6, 0, 0), new SpriteFrame(7, 5, 0, -16) });
	
	public static final Sprite PLAYER_PLEFT0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(2, 6, 0, 0), new SpriteFrame(2, 5, 0, -16) });
	public static final Sprite PLAYER_PLEFT1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(10, 6, 0, 0), new SpriteFrame(10, 5, 0, -16) });
	public static final Sprite PLAYER_PLEFT2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(11, 6, 0, 0), new SpriteFrame(11, 5, 0, -16) });
	
	public static final Sprite PLAYER_PRIGHT0 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(3, 6, 0, 0), new SpriteFrame(3, 5, 0, -16) });
	public static final Sprite PLAYER_PRIGHT1 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(8, 6, 0, 0), new SpriteFrame(8, 5, 0, -16) });
	public static final Sprite PLAYER_PRIGHT2 = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(9, 6, 0, 0), new SpriteFrame(9, 5, 0, -16) });
	
	// Shadows
	public static final Sprite BOX_SHADOW = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(5, 3, 0, 0), new SpriteFrame(6, 3, 16, 0), new SpriteFrame(5, 4, 0, 16), new SpriteFrame(6, 4, 16, 16) });
	public static final Sprite PLAYER_SHADOW = new Sprite(SpriteSheet.SHEET, new SpriteFrame[] { new SpriteFrame(7, 3, 0, 0), new SpriteFrame(8, 3, 16, 0), new SpriteFrame(7, 4, 0, 16), new SpriteFrame(8, 4, 16, 16) });
	
	private SpriteSheet sheet;
	private SpriteFrame[] tiles;
	
	public Sprite(SpriteSheet sheet, SpriteFrame[] tiles) {
		this.sheet = sheet;
		this.tiles = tiles;
	}
	
	public void render(Bitmap bmp, int x, int y) {
		for (SpriteFrame f : tiles) {
			sheet.blitSpriteTo(bmp, x + f.xoff, y + f.yoff, f.xt, f.yt);
		}
	}
	
	public void renderScaled(Bitmap bmp, int x, int y, float sx, float sy) {
		for (SpriteFrame f : tiles) {
			sheet.blitSpriteToScaled(bmp, x + f.xoff, y + f.yoff, f.xt, f.yt, sx, sy);
		}
	}
}
