package skeleton.view.gfx;

import java.awt.Graphics;

/**
 * An abstract class representing a shape.
 */
public abstract class Shape {
	public int x;
	public int y;
	
	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Draws the shape.
	 */
	public abstract void draw(Graphics g, int xoff, int yoff);
}
