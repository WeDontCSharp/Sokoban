package skeleton.view.gfx;

import java.awt.Graphics;

public abstract class Shape {
	public int x;
	public int y;
	
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(Graphics g, int xoff, int yoff);
}
