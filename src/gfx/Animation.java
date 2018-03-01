package gfx;

public class Animation {
	
	private Sprite[] sprites;
	private int curFrame = 0;
	private int tickCount;
	private int speed;
	
	public Animation(Sprite[] sprites, int sp) {
		this.sprites = sprites;
		this.curFrame = 0;
		this.tickCount = 0;
		this.speed = sp;
	}
	
	public void update() {
		++tickCount;
		if (tickCount % speed == 0) {
			curFrame = (curFrame + 1) % sprites.length;
		}
	}
	
	public void render(Bitmap bmp, int x, int y) {
		sprites[curFrame].render(bmp, x, y);
	}
	
	public void reset() {
		this.tickCount = 0;
		this.curFrame = 0;
	}
}
