package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Shape {
	public Color color;
	
	public int x;
	public int y;
	
	public int r;
	
	public Circle(int x, int y, int r, Color color) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		g.setColor(this.color);
		g.fillOval(this.x + xoff, this.y + yoff, this.r, this.r);
	}
}
