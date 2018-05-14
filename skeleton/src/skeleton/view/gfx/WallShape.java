package skeleton.view.gfx;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Draws a wall.
 */
public class WallShape extends Shape {

	public WallShape(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g, int xoff, int yoff) {
		g.setColor(new Color(60, 60, 60));
		g.fillRect(x + xoff, y + yoff, GraphicsView.UNIT_WIDTH, GraphicsView.UNIT_WIDTH);
	}

}
